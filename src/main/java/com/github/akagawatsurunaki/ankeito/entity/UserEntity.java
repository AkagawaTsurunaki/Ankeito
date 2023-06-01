package com.github.akagawatsurunaki.ankeito.entity;

import com.github.akagawatsurunaki.ankeito.common.enumeration.UserType;
import lombok.Data;

@Data
public class UserEntity {
    /**
     用户的ID(唯一且尽量不要更改)
     */
    String id;

    /**
     * 用户名
     */
    String username;

    /**
     * 用户密码
     */
    String password;

    /**
     * 用户类型
     */
    String roleId;

    String startTime;

    String stopTime;

    String status;

    String createdBy;

    String creationDate;

    String lastUpdatedBy;

    String lastUpdateDate;


}
