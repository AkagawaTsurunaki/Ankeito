package com.github.akagawatsurunaki.ankeito.api.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponseEntity {
    String code;
    String message;
    Object data;
}
