package com.github.akagawatsurunaki.ankeito.param;

import com.github.akagawatsurunaki.ankeito.api.param.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ModifyUserParamTest {


    @Test
    public void testEqualsAndHashCode() {
        String id = "1234567890abcdef";
        String username = "testUser";
        String password = "testPassword";
        UserRole userRole = UserRole.ADMIN;
        UserStatus userStatus = UserStatus.ENABLE;

        AddUserParam addUserParam = new AddUserParam();
        addUserParam.setUsername(username);
        addUserParam.setPassword(password);

        ModifyUserParam param1 = ModifyUserParam.builder()
                .id(id)
                .userRole(userRole)
                .userStatus(userStatus)
                .build();
        ModifyUserParam param2 = ModifyUserParam.builder()
                .id(id)
                .userRole(userRole)
                .userStatus(userStatus)
                .build();
        ModifyUserParam param3 = ModifyUserParam.builder()
                .id("abcdef1234567890")
                .userRole(userRole)
                .userStatus(userStatus)
                .build();

        Assertions.assertEquals(param1, param2);
        Assertions.assertNotEquals(param1, param3);
        Assertions.assertEquals(param1.hashCode(), param2.hashCode());
        Assertions.assertNotEquals(param1.hashCode(), param3.hashCode());

        // 测试能否正确判断不同类的对象
        Assertions.assertNotEquals(param1, addUserParam);
    }

    @Test
    public void testToString() {
        String id = "1234567890abcdef";
        String username = "testUser";
        String password = "testPassword";
        UserRole userRole = UserRole.ADMIN;
        UserStatus userStatus = UserStatus.ENABLE;

        ModifyUserParam param = ModifyUserParam.builder()
                .id(id)
                .userRole(userRole)
                .userStatus(userStatus)
                .build();
        System.out.println("param = " + param);

    }


}
