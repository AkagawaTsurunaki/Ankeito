package com.github.akagawatsurunaki.ankeito.dao.entity;

import lombok.Data;

@Data
public class HttpResponseEntity {

    String code;
    Object data;
    String message;
}
