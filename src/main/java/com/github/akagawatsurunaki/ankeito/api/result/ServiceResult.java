package com.github.akagawatsurunaki.ankeito.api.result;

import com.github.akagawatsurunaki.ankeito.common.enumeration.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
public class ServiceResult<Data> {

    /**
     * 服务响应码，当该服务被调用后返回
     * 数字的含义可以自行规定，比如200表示正确，500表示异常
     */
    @Getter
    private ResultCode resultCode;

    /**
     * 返回的服务响应信息
     */
    @Getter
    private String message;

    /**
     * 传给上层的数据
     * <p>
     * Data是个泛型，比如可以是List&lt;UserEntity&gt;等
     */
    @Getter
    Data data;

    public static<Data> ServiceResult<Data> of(@NonNull ResultCode resultCode, @NonNull String message, @NonNull Data data) {
        return new ServiceResult<Data>(resultCode, message, data);
    }

    public static<Data> ServiceResult<Data> of(@NonNull ResultCode resultCode, @NonNull String message) {
        return new ServiceResult<Data>(resultCode, message, null);
    }

}
