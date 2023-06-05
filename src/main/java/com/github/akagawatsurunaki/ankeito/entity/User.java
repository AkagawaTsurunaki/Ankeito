package com.github.akagawatsurunaki.ankeito.entity;

import com.github.akagawatsurunaki.common.enumeration.UserRole;
import com.github.akagawatsurunaki.common.enumeration.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {

    // 最大40个字符
    String id;

    String username;

    String password;

    UserRole userRole;

    Date startTime;

    Date stopTime;

    UserStatus userStatus;

    String createdBy;

    Date creationTime;

    String lastUpdatedBy;

    Date lastUpdateTime;

}
