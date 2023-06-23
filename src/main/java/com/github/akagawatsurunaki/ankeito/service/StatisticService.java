package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.dto.InnerOption;
import com.github.akagawatsurunaki.ankeito.api.dto.QuestionStatisticDTO;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryStatisticParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseOptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetDetailMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.OptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QnnreMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QuestionMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    QuestionMapper questionMapper;
    ResponseSheetDetailMapper responseSheetDetailMapper;
    ResponseOptionMapper responseOptionMapper;
    OptionMapper optionMapper;

    QnnreMapper qnnreMapper;

    @Autowired
    StatisticService(QuestionMapper questionMapper,
                     ResponseSheetDetailMapper responseSheetDetailMapper,
                     ResponseOptionMapper responseOptionMapper,
                     OptionMapper optionMapper,
                     QnnreMapper qnnreMapper) {
        this.questionMapper = questionMapper;
        this.responseSheetDetailMapper = responseSheetDetailMapper;
        this.responseOptionMapper = responseOptionMapper;
        this.optionMapper = optionMapper;
        this.qnnreMapper = qnnreMapper;
    }

    /**
     * 根据查询参数获取问题的统计信息。
     *
     * @param queryStatisticParam 查询参数，包含问卷ID
     * @return 问题统计结果的服务结果(ServiceResult)，包含问题统计列表(List<QuestionStatisticDTO>)
     */
    public ServiceResult<List<QuestionStatisticDTO>> getQuestionStatistic(@NonNull QueryStatisticParam queryStatisticParam) {
        try {
            val qnnreId =
                    Optional.ofNullable(queryStatisticParam.getQnnreId()).orElseThrow(() -> new IllegalArgumentException("问卷ID" +
                            "必须被指定"));
            val questionStatisticDTOS = getQuestionStatisticDTOS(qnnreId);
            return ServiceResult.ofOK("成功统计问卷结果", questionStatisticDTOS);
        } catch (IllegalArgumentException e1) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e1.getMessage());
        } catch (NullPointerException e2) {
            return ServiceResult.of(ServiceResultCode.FAILED, e2.getMessage());
        }
    }

    /**
     * 获取指定问卷下的问题统计信息。
     *
     * @param qnnreId 问卷ID
     * @return 问题统计DTO列表(List < QuestionStatisticDTO >)
     * @throws NullPointerException 如果问卷下没有问题，则抛出空指针异常
     */
    private List<QuestionStatisticDTO> getQuestionStatisticDTOS(@NonNull String qnnreId) throws NullPointerException {
        val questions =
                Optional.ofNullable(questionMapper.selectByQnnrId(qnnreId)).orElseThrow(() -> new NullPointerException(
                        "该问卷下没有问题"));
        return questions.stream()
                .map(question -> getQuestionStatisticDTO(question.getQnnreId(), question.getId()))
                .toList();
    }

    /**
     * 获取指定问题的统计信息
     *
     * @param qnnreId    问卷ID
     * @param questionId 问题ID
     * @return QuestionStatisticDTO对象，包含问题的统计信息
     * @throws NullPointerException 如果问题不存在，则抛出空指针异常
     */
    private QuestionStatisticDTO getQuestionStatisticDTO(@NonNull String qnnreId, @NonNull Integer questionId) throws NullPointerException {
        val question = questionMapper.selectByQnnreIdAndQuestionId(qnnreId, questionId);
        val questionName = Optional.ofNullable(question).orElseThrow(() -> new NullPointerException("问题不存在"))
                .getContent();
        val questionCount = (int) responseSheetDetailMapper.countByQnnreIdAndQuestionId(qnnreId, questionId);

        val options =
                Optional.ofNullable(optionMapper.selectByQnnreIdAndQuestionId(qnnreId, questionId)).orElseThrow(() -> new NullPointerException("该问题下没有选项"));
        val innerOptionList = options.stream()
                .map(option -> {
                    int innerOptionCount = (int) responseOptionMapper.countByQnnreIdAndQuestionIdAndOptionId(qnnreId,
                            questionId, option.getId());
                    return InnerOption.builder()
                            .optionId(option.getId())
                            .optionContent(option.getContent())
                            .count(innerOptionCount)
                            .build();
                }).toList();

        // 计算percent
        val sum = innerOptionList.stream().mapToInt(InnerOption::getCount).sum();
        innerOptionList.forEach(
                innerOption -> innerOption.setPercent(((float) innerOption.getCount()) / ((float) sum))
        );

        return QuestionStatisticDTO.builder()
                .questionId(questionId)
                .questionName(questionName)
                .questionCount(questionCount)
                .optionList(innerOptionList)
                .build();
    }

}
