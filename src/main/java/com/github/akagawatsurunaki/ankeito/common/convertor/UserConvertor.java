package com.github.akagawatsurunaki.ankeito.common.convertor;

import com.github.akagawatsurunaki.ankeito.api.dto.UserDTO;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;


@AllArgsConstructor
public class UserConvertor {

    @NonNull
    private UserDTO userDTO;

    public User dtoToEntity() {
        try {
            return User.builder()
                    .id(userDTO.getId())
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .userRole(UserRole.getEnum(Integer.parseInt(userDTO.getRoleId())))
                    .startTime(userDTO.getStartTime())
                    .stopTime(userDTO.getStopTime())
                    .userStatus(UserStatus.getEnum(Integer.parseInt(userDTO.getStatus())))
                    .createdBy(userDTO.getCreatedBy())
                    .creationTime(userDTO.getCreationDate())
                    .lastUpdatedBy(userDTO.getLastUpdatedBy())
                    .lastUpdateTime(userDTO.getLastUpdateDate())
                    .build();
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
