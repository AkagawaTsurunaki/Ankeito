package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.login.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryUserListParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testAdd() {
        val param = new AddUserParam();
        param.setUsername("外那豚");
        param.setPassword("123456");
        param.setStartTime(new Date());
        param.setStopTime(new Date(1749985432 * 1000L));
        userService.add(param);
        userService.add(new AddUserParam());
    }

    @Test
    public void testUserLogin() {
        val param = new UserLoginParam();
        param.setUsername("外那豚");
        param.setPassword("123456");
        userService.userLogin(param);

        param.setUsername("不存在用户");
        param.setPassword("123456");
        userService.userLogin(param);
    }

    @Test
    public void testUpdate() {
        var param = new ModifyUserParam();
        param.setId("6cd548bb-cd5a-4f2e-8c72-14dfa40620e1");
        param.setStartTime(new Date(2021, 6, 1, 23, 12, 33));
        param.setStopTime(new Date(2021, 6, 1, 23, 12, 34));
        param.setUserStatus(UserStatus.ENABLE);
        param.setUserRole(UserRole.ADMIN);
        param.setUsername("zhangwu");
        param.setPassword("123123123");
        userService.modify(param);
        param.setId("6cd548bb");
        userService.modify(param);

        param = new ModifyUserParam();
        val user = userService.getUserByUsername("外那豚").getData();
        param.setId(user.getId());
        param.setUsername(user.getUsername());
        param.setPassword("65654654564");
        param.setUserRole(UserRole.ADMIN);
        param.setUserStatus(UserStatus.ENABLE);
        param.setStartTime(user.getStartTime());
        param.setStopTime(user.getStopTime());
        userService.modify(param);
    }

    @Test
    public void testGetUserPageAsList() {
        val param = new QueryUserListParam();
        param.setPageNum(1);
        param.setPageSize(1);
        param.setUserName("123");
        userService.getUserPageAsList(param);
    }


    @Test
    public void testGetUserByUsername() {

        val queryUserListParam = new QueryUserListParam();
        queryUserListParam.setUserName("外那豚");
        val userPageAsList = userService.getUserPageAsList(queryUserListParam);
        System.out.println("userPageAsList = " + userPageAsList);
    }

    @Test
    public void testDeleteUser() {
        val param = new DeleteUserParam();
        val user = userService.getUserByUsername("外那豚").getData();
        param.setId(user.getId());
        userService.delete(param);
    }

}
