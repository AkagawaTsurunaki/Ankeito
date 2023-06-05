package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ServiceResultCode {
    OK(666),
    FAILED(123);

    final int value;
}
