package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.dto.ResponseSheetDTO;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetDetailParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseSheet;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetMapper;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    ResponseSheetMapper responseSheetMapper;
    QnnreService qnnreService;

    ResponseService(ResponseSheetMapper responseSheetMapper, QnnreService qnnreService) {
        this.responseSheetMapper = responseSheetMapper;
        this.qnnreService = qnnreService;
    }

    /**
     * 根据指定的答卷 ID 查询详细的答卷信息
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
     * 根据指定的问卷 ID 查询所有的答卷 ResponseSheet
     *
     * @param queryResponseSheetParam 包含查询参数的实体类，其中 qnnreId 表示所需查询的问卷 ID
     * @return 返回 ServiceResult 对象，其中包含查询结果的 List<ResponseSheet> 类型数据，以及返回的操作信息
     */
    public ServiceResult<List<ResponseSheet>> getByQnnreId(@NonNull QueryResponseSheetParam queryResponseSheetParam) {
        val data = (Optional.ofNullable(queryResponseSheetParam.getQnnreId()).map(
                qnnreId -> responseSheetMapper.selectByQnnreId(qnnreId)
        ).orElse(new ArrayList<>()));
        return ServiceResult.ofOK("成功查询到" + data.size() + "份答卷", data);
    }

}
