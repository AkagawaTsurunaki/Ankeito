package com.github.akagawatsurunaki.ankeito.common.constant;

import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;

public class ServiceResultConst<Data> {

    public final ServiceResult<Data> DTO_TO_ENTITY_EXCEPTION_SERVICE_RESULT = ServiceResult.of(
            ServiceResultCode.DTO_TO_ENTITY_EXCEPTION,
            "在将 DTO 转化为 Entity 时发生异常"
    );
}
