package com.github.akagawatsurunaki.ankeito.api.result;

import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.NonNull;

@AllArgsConstructor
public class ServiceResult<Data> {

    /**
     * 服务响应码
     */
    @Getter
    ServiceResultCode code;

    /**
     * 服务响应信息
     */
    @Getter
    String message;

    /**
     * 服务响应数据 (可空)
     */
    @Getter
    Data data;

    public static <Data> ServiceResult<Data> of(@NonNull ServiceResultCode code, @NonNull String message) {
        return new ServiceResult<>(code, message, null);
    }

    public static <Data> ServiceResult<Data> of(@NonNull ServiceResultCode code, @NonNull String message,
                                                @NonNull Data data) {
        return new ServiceResult<>(code, message, data);
    }

}
