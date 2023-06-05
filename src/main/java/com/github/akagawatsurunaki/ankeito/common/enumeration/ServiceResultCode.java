package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ServiceResultCode {
    OK(666),
    FAILED(123),
    NO_SUCH_ENTITY(12),
    DTO_TO_ENTITY_EXCEPTION(121),
    ;

    final int value;
}
