package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.QueryUserListParam;
import com.github.akagawatsurunaki.ankeito.api.param.UserLoginParam;
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
