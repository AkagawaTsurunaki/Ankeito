package com.github.akagawatsurunaki.ankeito.common.utils;

import lombok.val;

import java.util.UUID;

public class UUIDUtil {

    public static String getOneUUID() {
        val s = UUID.randomUUID().toString();
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        val ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getOneUUID();
        }
        return ss;
    }


}
