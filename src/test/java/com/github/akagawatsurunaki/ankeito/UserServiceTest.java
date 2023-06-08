package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryUserListParam;
import com.github.akagawatsurunaki.ankeito.api.param.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import com.github.akagawatsurunaki.ankeito.service.UserService;
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
    public void testUserLogin() {
        val param = new UserLoginParam();
        param.setUsername("及sad");
        param.setPassword("1232323");
        userService.userLogin(param);

        param.setUsername("admin");
        param.setPassword("1234567890");
        userService.userLogin(param);
    }

    @Test
    public void testUpdate() {
        val param = new ModifyUserParam();
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
    public void testAdd() {
        val param = new AddUserParam();
        param.setUsername("好日子");
        param.setPassword("123");
        param.setStartTime(new Date());
        param.setStopTime(new Date());
        userService.add(param);
    }

}
