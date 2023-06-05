package com.github.akagawatsurunaki.ankeito.common.convertor;

import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;

@AllArgsConstructor
public class ServiceResultConvertor<Data> {

    @NonNull
    private ServiceResult<Data> serviceResult;

    public HttpResponseEntity toHttpResponseEntity() {
        return HttpResponseEntity.builder()
                .code(String.valueOf(serviceResult.getCode().getValue()))
                .message(serviceResult.getMessage())
                .data(serviceResult.getData())
                .build();
    }

}
