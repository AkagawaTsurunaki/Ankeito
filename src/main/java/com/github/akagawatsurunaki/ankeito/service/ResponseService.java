package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.dto.ResponseSheetDTO;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetDetailParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseSheet;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import com.github.akagawatsurunaki.ankeito.mapper.UserMapper;
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

    ResponseService(ResponseSheetMapper responseSheetMapper,
                    QnnreService qnnreService,
                    QnnreMapper qnnreMapper,
                    UserMapper userMapper) {
        this.responseSheetMapper = responseSheetMapper;
        this.qnnreService = qnnreService;
        this.qnnreMapper = qnnreMapper;
        this.userMapper = userMapper;
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

    /**
     * 新增一份答卷
     *
     * @param addResponseSheetParam 包含新增参数的实体类，其中 respondentId 表示答卷填写人 ID，qnnreId 表示所属问卷 ID
     * @return 返回 ServiceResult 对象，其中包含新增的 ResponseSheet 类型数据，以及返回的操作信息
     */
    public ServiceResult<ResponseSheet> addResponseSheet(@NonNull AddResponseSheetParam addResponseSheetParam) {
        val user =
                Optional.ofNullable(addResponseSheetParam.getRespondentId()).map(getRespondentId -> userMapper.selectById(getRespondentId));
        val qnnre =
                Optional.ofNullable(addResponseSheetParam.getQnnreId()).map(qnnreId -> qnnreMapper.selectById(qnnreId));

        if (user.isPresent() && qnnre.isPresent()) {
            val data = ResponseSheet.builder()
                    .id(UUID.randomUUID().toString())
                    .qnnreId(user.get().getId())
                    .qnnreName(qnnre.get().getName())
                    .respondentId(user.get().getId())
                    .respondentName(user.get().getUsername())
                    .finishedTime(new Date())
                    .build();
            return ServiceResult.ofOK("成功增加一张问卷", data);
        }
        return ServiceResult.of(ServiceResultCode.NO_SUCH_ENTITY, "用户或问卷不存在");
    }

}
