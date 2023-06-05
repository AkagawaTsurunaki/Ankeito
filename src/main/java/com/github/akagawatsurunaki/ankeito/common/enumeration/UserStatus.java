package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserStatus {
    ENABLE("启用"),
    DISABLE("不启用");

    final String value;
}
