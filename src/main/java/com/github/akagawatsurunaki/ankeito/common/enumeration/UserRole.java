package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserRole {
    ADMIN(1, "管理员"),
    NO_ROLE(0, "无身份");

    final int value;
    final String chinese;

}
