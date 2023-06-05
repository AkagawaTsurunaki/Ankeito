package com.github.akagawatsurunaki.ankeito;

import cn.hutool.core.lang.UUID;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.entity.User;
import lombok.val;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class EqualTest {

    public void userEqualTest() {
        val user1 = User.builder()
                .id(UUID.fastUUID().toString())
                .username("1278dui")
                .password("98dfdfowifoi")
                .userRole(UserRole.NO_ROLE)
                .startTime(new Date())
                .stopTime(new Date())
                .userStatus(UserStatus.DISABLE)
                .build();

        val user2 = User.builder()
                .id(UUID.fastUUID().toString())
                .username("1278dui")
                .password("98dfdfowi6456654i")
                .userRole(UserRole.NO_ROLE)
                .startTime(new Date())
                .stopTime(new Date())
                .userStatus(UserStatus.DISABLE)
                .build();

        assert !user1.equals(user2);
    }

}
