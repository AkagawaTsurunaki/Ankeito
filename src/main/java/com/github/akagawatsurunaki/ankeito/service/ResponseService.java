package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.util.RandomUtil;
import com.github.akagawatsurunaki.ankeito.api.dto.ResponseSheetDTO;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetDetailParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.User;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseSheet;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.mapper.UserMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseOptionMapper;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetMapper;
import com.github.akagawatsurunaki.ankeito.mapper.qnnre.QnnreMapper;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResponseService {
    ResponseSheetMapper responseSheetMapper;
    QnnreService qnnreService;
    QnnreMapper qnnreMapper;
    UserMapper userMapper;
    ResponseOptionMapper responseOptionMapper;

    ResponseService(ResponseSheetMapper responseSheetMapper,
                    QnnreService qnnreService,
                    QnnreMapper qnnreMapper,
                    UserMapper userMapper,
                    ResponseOptionMapper responseOptionMapper) {
        this.responseSheetMapper = responseSheetMapper;
        this.qnnreService = qnnreService;
        this.qnnreMapper = qnnreMapper;
        this.userMapper = userMapper;
        this.responseOptionMapper = responseOptionMapper;
    }

    /**
     * 根据指定的答卷 ID 查询详细的答卷信息
     *
     * @param queryResponseSheetDetailParam 包含查询参数的实体类，其中 responseSheetId 表示所需查询的答卷 ID
     * @return 返回 ServiceResult 对象，其中包含查询结果的 ResponseSheetDTO 类型数据，以及返回的操作信息
     */
    public ServiceResult<ResponseSheetDTO> getResponseSheetDetail(@NonNull QueryResponseSheetDetailParam queryResponseSheetDetailParam) {
        try {
            val responseSheetDTO = Optional.ofNullable(queryResponseSheetDetailParam.getResponseSheetId()).map(
                    sheetId -> {
                        val responseSheet = responseSheetMapper.selectById(sheetId);
                        return Optional.ofNullable(responseSheet).map(
                                it -> {
                                    val qnnreId = it.getQnnreId();
                                    val qnnreDTO =
                                            Optional.ofNullable(qnnreService.get(qnnreId).getData()).orElseThrow(() -> new NullPointerException("问卷不存在"));
                                    return ResponseSheetDTO.builder().qnnreDTO(qnnreDTO).responseSheet(it).build();
                                }
                        ).orElseThrow(() -> new NullPointerException("答卷不存在"));
                    }
            ).orElseThrow(() -> new IllegalArgumentException("答卷ID未指定"));

            val responseOptions =
                    responseOptionMapper.selectByResponseSheetId(responseSheetDTO.getResponseSheet().getId());
            responseOptions.forEach(
                    responseOption ->
                            responseSheetDTO.getQnnreDTO().getQuestionDTOList().forEach(
                                    questionDTO ->
                                            questionDTO.getOptionList().forEach(
                                                    optionDTO -> {
                                                        val option = optionDTO.getOption();
                                                        if (option.getQuestionId().equals(responseOption.getQuestionId())
                                                                && option.getQnnreId().equals(responseOption.getQnnreId())
                                                                && option.getId().equals(responseOption.getOptionId())
                                                        ) {
                                                            optionDTO.setSelected(true);
                                                        }
                                                    }
                                            )
                            )
            );

            return ServiceResult.ofOK("查询到指定答卷", responseSheetDTO);

        } catch (NullPointerException e) {
            return ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, e.getMessage());
        } catch (IllegalArgumentException e1) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e1.getMessage());
        }
    }


    /**
     * 根据指定的项目 ID 查询所有的答卷 ResponseSheet
     *
     * @param queryResponseSheetParam 包含查询参数的实体类，其中 projectId 表示所需查询的项目 ID
     * @return 返回 ServiceResult 对象，其中包含查询结果的 List<ResponseSheet> 类型数据，以及返回的操作信息
     */
    public ServiceResult<List<ResponseSheet>> getResponseSheet(@NonNull QueryResponseSheetParam queryResponseSheetParam) {

        try {
            val qnnreList = qnnreMapper.selectByProjectId(Optional.ofNullable(queryResponseSheetParam.getProjectId())
                    .orElseThrow(() -> new IllegalArgumentException("ProjectId必须被指定")));

            List<ResponseSheet> result = new ArrayList<>();
            qnnreList.stream().map(Qnnre::getId).forEach(qnnreId -> result.addAll(responseSheetMapper.selectByQnnreId(qnnreId)));

            return ServiceResult.ofOK("成功查询到" + result.size() + "份答卷", result);
        } catch (IllegalArgumentException e) {
            return ServiceResult.of(ServiceResultCode.ILLEGAL_PARAM, e.getMessage());
        }

    }

    public ServiceResult<ResponseSheetDTO> createEmptyResponseSheet(@NonNull AddResponseSheetParam addResponseSheetParam) {
        try {
            val responseSheet =
                    Optional.ofNullable(addResponseSheet(addResponseSheetParam)).orElseThrow(() -> new NullPointerException(
                            "新建的答卷不存在"));
            // 这里由于答卷是空白的, 所以不需要加载选项选择情况
            val responseSheetDTO = Optional.ofNullable(responseSheet.getId()).map(
                    sheetId -> Optional.of(responseSheet).map(
                            it -> {
                                val qnnreId = it.getQnnreId();
                                val qnnreDTO =
                                        Optional.ofNullable(qnnreService.get(qnnreId).getData()).orElseThrow(() -> new NullPointerException("问卷不存在"));
                                return ResponseSheetDTO.builder().qnnreDTO(qnnreDTO).responseSheet(it).build();
                            }
                    ).orElseThrow(() -> new NullPointerException("答卷不存在"))
            ).orElseThrow(() -> new IllegalArgumentException("答卷ID未指定"));

            return ServiceResult.ofOK("新建答卷成功", responseSheetDTO);
        } catch (NullPointerException e) {
            return ServiceResult.of(ServiceResultCode.FAILED, e.getMessage());
        }
    }


    /**
     * 新增一份答卷
     *
     * @param addResponseSheetParam 包含新增参数的实体类，其中 respondentId 表示答卷填写人 ID，qnnreId 表示所属问卷 ID
     */
    private ResponseSheet addResponseSheet(@NonNull AddResponseSheetParam addResponseSheetParam) {
        val user =
                Optional.ofNullable(addResponseSheetParam.getRespondentId()).map(getRespondentId -> userMapper.selectById(getRespondentId));
        val qnnre =
                Optional.ofNullable(addResponseSheetParam.getQnnreId()).map(qnnreId -> qnnreMapper.selectById(qnnreId));
        val data = ResponseSheet.builder()
                .id(UUID.randomUUID().toString())
                .qnnreId(qnnre.map(Qnnre::getId).orElseThrow(() -> new NullPointerException("必须指定问卷ID")))
                .qnnreName(qnnre.map(Qnnre::getName).orElseThrow(() -> new NullPointerException("必须指定问卷名称")))
                .respondentId(user.map(User::getId).orElse(UUID.randomUUID().toString()))
                .respondentName(user.map(User::getUsername).orElse("TempUser" + RandomUtil.randomNumbers(20)))
                .finishedTime(new Date())
                .build();
        return responseSheetMapper.insert(data) == 1 ? data : null;
    }

}
