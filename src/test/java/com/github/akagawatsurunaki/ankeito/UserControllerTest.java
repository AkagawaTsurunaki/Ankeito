package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.controller.UserController;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Test
    public void testUserLogin() {
        val param = new UserLoginParam();
        param.setUsername("你好");
        param.setPassword("asdasd");
        userController.userLogin(param);
    }

    @Test
    public void testAddUserInfo() {
        val param = new AddUserParam();
        param.setUsername("侧你妈");
        param.setPassword("23894sdaofjioa");
        param.setStartTime(new Date());
        param.setStopTime(new Date());
        userController.addUserInfo(param);
    }

    @Test
    public void testModifyUser() {
        val param = new ModifyUserParam();
        param.setUsername("侧你妈");
        param.setPassword("23894sdaofjioa");
        param.setStartTime(new Date());
        param.setStopTime(new Date());
        param.setUserRole(UserRole.NO_ROLE);
        param.setUserStatus(UserStatus.DISABLE);
        userController.modifyUser(param);
    }

    @Test
    public void testDeleteUser() {
        val param = new DeleteUserParam();
        param.setId("sdofs98df89df89");
        userController.deleteUser(param);
    }

}
