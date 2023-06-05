package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserRole {
    ADMIN(1, "管理员"),
    NO_ROLE(0, "无身份");

    final int value;
    final String chinese;

    public static UserRole getEnum(int value) {
        return Arrays.stream(UserRole.values()).filter(it -> it.value == value).findFirst().orElse(ADMIN);
    }
}
