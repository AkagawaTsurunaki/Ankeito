package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.param.query.QueryResponseSheetParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.entity.answer.ResponseSheet;
import com.github.akagawatsurunaki.ankeito.mapper.answer.ResponseSheetMapper;
import lombok.val;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResponseService {
    ResponseSheetMapper responseSheetMapper;

    ResponseService(ResponseSheetMapper responseSheetMapper) {
        this.responseSheetMapper = responseSheetMapper;
    }

    /**
     * 根据指定的答卷 ID 查询详细的答卷信息
     */
//    public ServiceResult<Objects> getDetail() {
//
//    }

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
