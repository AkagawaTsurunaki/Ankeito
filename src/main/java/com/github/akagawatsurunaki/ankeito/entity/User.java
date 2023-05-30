package com.github.akagawatsurunaki.ankeito.entity;

import com.github.akagawatsurunaki.ankeito.common.enumeration.UserType;
import lombok.Data;

@Data
public class User {
    /**
     用户的ID(唯一且尽量不要更改)
     */
    Integer id;

    /**
     * 用户名
     */
    String username;

    /**
     * 用户密码(已加密)
     */
    String passwordMd5;

    /**
     * 用户类型
     */
    UserType userType;


}
