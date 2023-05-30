package com.github.akagawatsurunaki.ankeito.common.enumeration;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserType {
    SYSTEM_ADMIN("系统管理员"),
    FACTORY_ADMIN("云工厂管理员");

    public final String value;
}
