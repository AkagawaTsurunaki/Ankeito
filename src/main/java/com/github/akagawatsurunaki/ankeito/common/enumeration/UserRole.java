package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    NO_ROLE(0, "无身份");

    final int value;
    final String chinese;

}
