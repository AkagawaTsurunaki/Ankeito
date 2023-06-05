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
        param.setUsername(null);
        param.setPassword(null);
        userService.userLogin(param);
    }

    @Test
    public void testGetUserPageAsList() {
        userService.getUserPageAsList(new QueryUserListParam());
    }


    @Test
    public void testAdd() {
        val param = new AddUserParam();
        param.setUsername("好日子");
        param.setPassword(null);
        param.setStartTime(new Date());
        param.setStopTime(new Date());
        userService.add(param);
    }

}
