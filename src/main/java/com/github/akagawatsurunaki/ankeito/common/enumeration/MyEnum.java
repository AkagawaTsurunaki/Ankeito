package com.github.akagawatsurunaki.ankeito.common.enumeration;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import lombok.AllArgsConstructor;
import lombok.val;

@AllArgsConstructor
public enum MyEnum {
    ;
    final int value;
    final String chinese;

    public static <E extends MyEnum> E valueOf(int value) {
        try {
            for (var myEnum : E.values()) {
                if (myEnum.value == value) {
                    val typeReference = new TypeReference<E>() {
                    };
                    return Convert.convert(typeReference, myEnum);
                }
            }
        } catch (ClassCastException e) {
            return null;
        }
        return null;
    }

}
