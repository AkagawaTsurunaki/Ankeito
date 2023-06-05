package com.github.akagawatsurunaki.ankeito.api.param;

import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class ModifyUserParam extends AddUserParam{
    String id;
    UserRole userRole;
    UserStatus userStatus;

}
