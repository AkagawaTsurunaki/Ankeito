package com.github.akagawatsurunaki.ankeito.common.convertor;

import com.github.akagawatsurunaki.ankeito.api.dto.UserDTO;
import com.github.akagawatsurunaki.ankeito.entity.User;
import org.springframework.lang.NonNull;

public class UserConvertor {

    public User dtoToEntity(@NonNull UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password( userDTO.getPassword())
                .userRole(
                        // TODO: convert
                        //     userDTO.getRoleId();
                        null
                )
                .startTime(    userDTO.getStartTime())
                .stopTime(  userDTO.getStopTime())
                .userStatus(
                       // TODO : userDTO.getStatus();
                       null
                )
                .createdBy(userDTO.getCreatedBy())
                .creationTime(userDTO.getCreationDate())
                .lastUpdatedBy(userDTO.getLastUpdatedBy())
                .lastUpdateTime(userDTO.getLastUpdateDate())
                .build();
    }

}
