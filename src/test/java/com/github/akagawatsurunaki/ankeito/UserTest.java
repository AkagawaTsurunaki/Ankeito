package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserTest {
    @Test
    public void testUserBuilder() {
        String id = "1234567890abcdef";
        String username = "testUser";
        String password = "testPassword";
        Date startTime = new Date();
        Date stopTime = new Date();
        UserRole userRole = UserRole.ADMIN;
        UserStatus userStatus = UserStatus.DISABLE;
        String createdBy = "creator";
        Date creationTime = new Date();
        String lastUpdatedBy = "updater";
        Date lastUpdateTime = new Date();

        User user = User.builder()
                .id(id)
                .username(username)
                .password(password)
                .startTime(startTime)
                .stopTime(stopTime)
                .userRole(userRole)
                .userStatus(userStatus)
                .createdBy(createdBy)
                .creationTime(creationTime)
                .lastUpdatedBy(lastUpdatedBy)
                .lastUpdateTime(lastUpdateTime)
                .build();

        Assertions.assertEquals(id, user.getId());
        Assertions.assertEquals(username, user.getUsername());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(startTime, user.getStartTime());
        Assertions.assertEquals(stopTime, user.getStopTime());
        Assertions.assertEquals(userRole, user.getUserRole());
        Assertions.assertEquals(userStatus, user.getUserStatus());
        Assertions.assertEquals(createdBy, user.getCreatedBy());
        Assertions.assertEquals(creationTime, user.getCreationTime());
        Assertions.assertEquals(lastUpdatedBy, user.getLastUpdatedBy());
        Assertions.assertEquals(lastUpdateTime, user.getLastUpdateTime());
    }

}
