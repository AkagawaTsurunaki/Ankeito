package com.github.akagawatsurunaki.ankeito.util;

import lombok.NonNull;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Util {

    private static MessageDigest messageDigest;

    /**
     * 将明文密码转化为md5加密后的密码
     *
     * @param rawPassword 明文密码字符串
     * @return md5加密后的字符串
     */
    @SneakyThrows
    public static String getMd5Password(@NonNull String rawPassword) {

        if (messageDigest == null) {
            messageDigest = MessageDigest.getInstance("MD5");
        }

        byte[] bytes = messageDigest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
