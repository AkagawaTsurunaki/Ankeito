package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.akagawatsurunaki.ankeito.api.dto.QnnreDTO;
import com.github.akagawatsurunaki.ankeito.api.dto.QuestionDTO;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddOptionParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQuestionParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryQnnreListParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.OptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QnnreMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QuestionMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 问卷服务类，提供查询问卷类型和添加问卷功能
 */
@Service
public class QnnreService {

    QnnreMapper qnnreMapper;

    QuestionMapper questionMapper;
    OptionMapper optionMapper;

    /**
     * 使用QnnreMapper进行依赖注入
     *
     * @param qnnreMapper QnnreMapper对象
     */
    @Autowired
    QnnreService(QnnreMapper qnnreMapper,
                 QuestionMapper questionMapper,
                 OptionMapper optionMapper) {
        this.qnnreMapper = qnnreMapper;
        this.questionMapper = questionMapper;
        this.optionMapper = optionMapper;
    }

    /**
     * 获取所有问卷类型
     *
     * @return 返回ServiceResult对象，其中包含查询结果和执行结果信息
     */
    public ServiceResult<List<String>> getQnnreType() {
        val data = Arrays.stream(QnnreType.values())
                .map(it -> it.value).toList();
        return ServiceResult.ofOK("查询到" + data.size() + "项目类型", data);
    }

    /**
     * 添加问卷
     *
     * @param addQnnreParam 添加问卷所需参数
     * @return 返回ServiceResult对象，其中包含添加的问卷对象和执行结果信息
     */
    public ServiceResult<Qnnre> addQnnre(@NonNull AddQnnreParam addQnnreParam) {
        try {
            val qnnre = Qnnre.builder()
                    .id(UUID.randomUUID().toString())
                    .name(Optional.ofNullable(addQnnreParam.getName()).orElse("未命名"))
                    .projectId(Optional.ofNullable(addQnnreParam.getProjectId()).orElseThrow(() -> new IllegalArgumentException("项目ID必须被指定")))
                    .description(Optional.ofNullable(addQnnreParam.getDescription()).orElse("未填写调查说明"))
                    .startTime(Optional.ofNullable(addQnnreParam.getStartTime()).orElse(new Date()))
                    // 如果用户没填写, 自动设定结束时间为次月
                    .stopTime(Optional.ofNullable(addQnnreParam.getStopTime()).orElse(DateUtil.nextMonth())).build();
            qnnreMapper.insert(qnnre);
            return ServiceResult.ofOK("问卷创建成功", qnnre);

        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        }
    }

    /**
     * 根据问卷ID获取问卷对象
     *
     * @param queryQnnreListParam 查询问卷所需参数
     * @return 返回ServiceResult对象，其中包含执行结果信息和查询的问卷对象（如果存在）
     */
    public ServiceResult<Qnnre> getQnnre(@NonNull QueryQnnreListParam queryQnnreListParam) {
        return Optional.ofNullable(queryQnnreListParam.getId())
                .map(id -> qnnreMapper.selectById(id))
                .map(value -> ServiceResult.ofOK("查询到指定的问卷", value))
                .orElseGet(() -> ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, "问卷不存在"));
    }

    public ServiceResult<QnnreDTO> deleteQnnre(@NonNull DeleteQnnreParam deleteQnnreParam) {
        try {
            val qnnreDTOServiceResult = get(deleteQnnreParam.getQnnreId());
            Optional.ofNullable(qnnreDTOServiceResult.getData()).ifPresentOrElse(
                    qnnre -> {
                        qnnreMapper.deleteById(qnnre.getQnnre());
                        qnnre.getQuestionDTOList().forEach(questionDTO -> {
                            questionMapper.deleteById(questionDTO.getQuestion());
                            questionDTO.getOptionList().forEach( option -> optionMapper.deleteById(option));
                        });
                    }, () -> {
                        throw new NullPointerException("删除失败, 问卷不存在");
                    }
            );
            return qnnreDTOServiceResult.with("问卷删除成功");
        } catch (NullPointerException e1) {
            return ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, e1.getMessage());
        }
    }

    public ServiceResult<QnnreDTO> save(@NonNull ModifyQnnreParam modifyQnnreParam) {
        try {
            deleteQnnre(DeleteQnnreParam.builder().qnnreId(modifyQnnreParam.getQnnreId()).build());
            val modifyQnnreServiceResult = modifyQnnre(modifyQnnreParam.getQnnreId(),
                    modifyQnnreParam.getQnnreTitle(),
                    modifyQnnreParam.getQnnreDescription());
            if (ObjectUtil.notEqual(ServiceResultCode.OK, modifyQnnreServiceResult.getCode())) {
                return modifyQnnreServiceResult;
            }
            Arrays.stream(modifyQnnreParam.getAddQuestionParams()).forEach(addQuestionParam -> {
                addQuestionParam.setQnnreId(modifyQnnreParam.getQnnreId());
                QnnreService.this.addMultipleChoiceQuestion(addQuestionParam);
            });
            Arrays.stream(modifyQnnreParam.getAddOptionParams()).forEach(this::addOptions);
            return get(modifyQnnreParam.getQnnreId()).with("问卷修改保存成功");
        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        }
    }

    public ServiceResult<QnnreDTO> get(@NonNull String qnnreId) {
        try {
            List<QuestionDTO> questionDTOList = new ArrayList<>();
            val qnnre =
                    Optional.ofNullable(qnnreMapper.selectById(qnnreId)).orElseThrow(() -> new NullPointerException(
                            "此问卷不存在"));
            // 获取该问卷下的所有问题
            val questions =
                    Optional.ofNullable(questionMapper.selectByQnnrId(qnnreId)).orElseThrow(() -> new NullPointerException("此问题不存在"));
            questions.forEach(
                    question -> questionDTOList.add(QuestionDTO.builder()
                            .question(question)
                            .optionList(Optional.ofNullable(optionMapper.selectByQuestionId(question.getId()))
                                    .orElseThrow(() -> new NullPointerException("此问题下的选项不存在")))
                            .build())
            );
            val data = QnnreDTO.builder().qnnre(qnnre).questionDTOList(questionDTOList).build();
            return ServiceResult.ofOK("成功获取问卷", data);

        } catch (NullPointerException e) {
            return ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, e.getMessage());
        }
    }

    private void addOptions(@NonNull AddOptionParam addOptionParam) throws IllegalArgumentException {
        List<Option> options = new ArrayList<>();
        Optional.ofNullable(addOptionParam.getContent()).ifPresentOrElse(
                contents -> Arrays.stream(contents).forEach(
                        content -> options.add(
                                Option.builder()
                                        .id(ArrayUtil.indexOf(contents, content))
                                        .content(content)
                                        .questionId(Optional.ofNullable(addOptionParam.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("选项必须依赖于指定的问题")))
                                        .build()
                        )
                ),
                () -> {
                    throw new IllegalArgumentException("必须至少有1个选项");
                }
        );
        options.forEach(optionMapper::insert);
    }

    private void addMultipleChoiceQuestion(@NonNull AddQuestionParam addQuestionParam) throws IllegalArgumentException {
        val question = Question.builder()
                .id(Optional.ofNullable(addQuestionParam.getIndex()).orElseThrow(() -> new
                        IllegalArgumentException(
                        "问题的ID必须被指定")))
                .qnnreId(Optional.ofNullable(addQuestionParam.getQnnreId()).orElseThrow(() -> new
                        IllegalArgumentException("问题必须依赖于一个已有的问卷")))
                .content(Optional.ofNullable(addQuestionParam.getProblemName()).orElseThrow(() -> new
                        IllegalArgumentException("题目标题不能为空")))
                .required(addQuestionParam.getMustAnswer() ? Required.REQUIRED : Required.OPTIONAL)
                .type(Optional.ofNullable(addQuestionParam.getType()).map(QuestionType::valueOf).orElse
                        (QuestionType.SINGLE_CHOICE_QUESTION))
                .build();
        questionMapper.insert(question);
    }

    private ServiceResult<QnnreDTO> modifyQnnre(String qnnreId, String qnnreTitle, String qnnreDescription) {
        try {
            Optional.ofNullable(qnnreId).ifPresent(
                    id -> {
                        Optional.ofNullable(qnnreMapper.selectById(id)).ifPresentOrElse(
                                qnnre -> {
                                    qnnre.setName(Optional.ofNullable(qnnreTitle).orElseThrow(() -> new IllegalArgumentException("问卷名不能为空")));
                                    qnnre.setDescription(Optional.ofNullable(qnnreDescription).orElse(""));
                                    qnnreMapper.updateById(qnnre);
                                },
                                () -> {
                                    throw new NullPointerException("该问卷不存在");
                                }
                        );
                    }
            );
            return ServiceResult.ofOK("成功修改问卷");
        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        } catch (NullPointerException e1) {
            return ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, e1.getMessage());
        }
    }

}
