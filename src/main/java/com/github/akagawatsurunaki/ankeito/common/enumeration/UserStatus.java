package com.github.akagawatsurunaki.ankeito.common.enumeration;

import com.github.akagawatsurunaki.ankeito.entity.User;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum UserStatus {
    ENABLE(1, "启用"),
    DISABLE(0, "不启用");

    final int value;
    final String chinese;

    /**
     *
     * 根据指定 value 值获取对应的 Enum
     *
     * @param value Enum 对应的 value
     * @return 对应的 UserStatus, 如果不存在默认为 DISABLE
     */
    public static UserStatus getEnum(int value) {
        return Arrays.stream(UserStatus.values()).filter( it -> it.value == value).findFirst().orElse(DISABLE);
    }

}
