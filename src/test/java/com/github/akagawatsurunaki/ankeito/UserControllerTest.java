package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.api.param.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.controller.UserController;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
