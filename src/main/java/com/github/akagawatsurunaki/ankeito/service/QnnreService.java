package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.date.DateUtil;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQuestionParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryQnnreListParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QnnreType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
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

    QuestionMapper mcqMapper;

    /**
     * 使用QnnreMapper进行依赖注入
     *
     * @param qnnreMapper QnnreMapper对象
     */
    @Autowired
    QnnreService(QnnreMapper qnnreMapper,
                 QuestionMapper questionMapper) {
        this.qnnreMapper = qnnreMapper;
        this.mcqMapper = questionMapper;
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

    public ServiceResult<Question> addMultipleChoiceQuestion(@NonNull AddQuestionParam addQuestionParam) {
        try {
            val question = Question.builder()
                    .id(addQuestionParam.getId())
                    .qnnrId(Optional.ofNullable(addQuestionParam.getQnnreId()).orElseThrow(() -> new IllegalArgumentException("问卷必须依赖于一个已有的项目")))
                    .content(Optional.ofNullable(addQuestionParam.getContent()).orElseThrow(() -> new IllegalArgumentException("题目标题不能为空")))
                    .required(Optional.ofNullable(addQuestionParam.getRequired()).map(Required::get).orElse(Required.OPTIONAL))
                    .type(Optional.ofNullable(addQuestionParam.getType()).map(QuestionType::valueOf).orElse(QuestionType.SINGLE_CHOICE_QUESTION))
                    .build();
            mcqMapper.insert(question);
            return ServiceResult.ofOK("成功添加1道选择题目", question);
        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        }
    }

}
