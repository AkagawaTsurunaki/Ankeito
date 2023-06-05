package com.github.akagawatsurunaki.ankeito.api.result;

import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@AllArgsConstructor
public class ServiceResult<Data> {

    /**
     * 服务响应码
     */
    ServiceResultCode code;

    /**
     * 服务响应信息
     */
    String message;

    /**
     * 服务响应数据 (可空)
     */
    Data data;

//    ServiceResult(@NonNull ServiceResultCode code,@NonNull String message, Data data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }

    public static <Data> ServiceResult<Data> of(@NonNull ServiceResultCode code, @NonNull String message) {
        return new ServiceResult<>(code, message, null);
    }

    public static <Data> ServiceResult<Data> of(@NonNull ServiceResultCode code, @NonNull String message,
                                                @NonNull Data data) {
        return new ServiceResult<>(code, message, data);
    }

}
