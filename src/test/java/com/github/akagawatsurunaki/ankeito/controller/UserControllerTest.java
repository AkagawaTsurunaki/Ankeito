package com.github.akagawatsurunaki.ankeito.controller;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.login.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryUserListParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.entity.User;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;
    String username;
    String password;
    Date startTime;
    Date stopTime;

    List<User> users;


    void init() {
        username = RandomUtil.randomString(RandomUtil.BASE_CHAR, 5);
        startTime = RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE);
        stopTime = RandomUtil.randomDate(new Date(), DateField.SECOND, Integer.MIN_VALUE, Integer.MAX_VALUE);
        password = RandomUtil.randomString(RandomUtil.BASE_CHAR_NUMBER, 16);
    }

    @Test
    public void testAddUserInfo() {
        val param = new AddUserParam();
        param.setUsername(username);
        param.setPassword(password);
        param.setStartTime(startTime);
        param.setStopTime(stopTime);
        val httpResponseEntity = userController.addUserInfo(param);
        System.out.println("httpResponseEntity = " + httpResponseEntity);
    }

    @Test
    public void testUserLogin() {
        val param = new UserLoginParam();
        param.setUsername(username);
        param.setPassword(password);
        val httpResponseEntity = userController.userLogin(param);
        System.out.println("httpResponseEntity = " + httpResponseEntity);
    }

    @Test
    public void testQueryUserList() {
        val queryUserListParam = new QueryUserListParam();
        val httpResponseEntity = userController.queryUserList(queryUserListParam);
        users = ((List<User>) Optional.ofNullable(httpResponseEntity.getData()).orElse(new ArrayList<>()));
        System.out.println("httpResponseEntity = " + httpResponseEntity);
    }



    @Test
    public void testModifyUser() {
        val param = new ModifyUserParam();
        param.setUsername(username);
        param.setPassword(password);
        param.setStartTime(new Date());
        param.setStopTime(new Date());
        param.setUserRole(UserRole.NO_ROLE);
        param.setUserStatus(UserStatus.DISABLE);
        userController.modifyUser(param);
    }

    @Test
    public void testDeleteUser() {
        val param = new DeleteUserParam();
        param.setId("12697df6-4acb-7e7b-9912-df7e28eaf51d");
        userController.deleteUser(param);
    }

}
