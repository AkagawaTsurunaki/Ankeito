package com.github.akagawatsurunaki.ankeito.dao.entity;

import com.github.akagawatsurunaki.ankeito.common.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserEntity implements Serializable {
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

    Date startTime;

    Date stopTime;

    String status;

    String createdBy;

    Date creationDate;

    String lastUpdatedBy;

    Date lastUpdateDate;


}
