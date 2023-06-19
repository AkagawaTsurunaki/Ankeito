package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.util.RandomUtil;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.login.UserLoginParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyUserParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryUserListParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserRole;
import com.github.akagawatsurunaki.ankeito.common.enumeration.UserStatus;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.UUID;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    static String username = "测试人员";
    static String password = "1145141919810";

    @Test
    @Order(1)
    public void testAdd() {
        val param = new AddUserParam();
        param.setUsername(username);
        param.setPassword(password);
        param.setStartTime(new Date());
        param.setStopTime(new Date(1749985432 * 1000L));
        userService.add(param);
        userService.add(new AddUserParam());
    }

    @Test
    @Order(2)
    public void testUserLogin() {
        var param = new UserLoginParam();
        param.setUsername(username);
        param.setPassword(password);
        var serviceResult = userService.userLogin(param);
        Assertions.assertEquals(ServiceResultCode.OK, serviceResult.getCode());
        Assertions.assertEquals("登录成功", serviceResult.getMessage());

        param.setUsername("不存在用户");
        param.setPassword("123456");
        serviceResult = userService.userLogin(param);
        Assertions.assertEquals(ServiceResultCode.FAILED, serviceResult.getCode());
        Assertions.assertEquals("用户名或密码错误", serviceResult.getMessage());

        param = new UserLoginParam();
        param.setUsername(username);
        param.setPassword("123456888");
        serviceResult = userService.userLogin(param);
        Assertions.assertEquals(ServiceResultCode.FAILED, serviceResult.getCode());
        Assertions.assertEquals("用户名或密码错误", serviceResult.getMessage());

    }

    @Test
    @Order(3)
    public void testModify() {
        var param = new ModifyUserParam();
        val userByUsername = userService.getUserByUsername(username).getData();

        param.setUserRole(RandomUtil.randomEle(UserRole.values()));
        param.setUserStatus(RandomUtil.randomEle(UserStatus.values()));
        param.setPassword("1919810555");
        param.setStartTime(new Date());
        param.setStopTime(new Date(1899985432 * 1000L));

        if (userByUsername != null) {
            param.setId(userByUsername.getId());
            param.setUsername(userByUsername.getUsername());

            val serviceResult = userService.modify(param);
            Assertions.assertEquals(ServiceResultCode.OK, serviceResult.getCode());
            Assertions.assertEquals("成功修改1条用户信息", serviceResult.getMessage());
        }

        param.setId(UUID.randomUUID().toString());
        val serviceResult = userService.modify(param);
        Assertions.assertEquals(ServiceResultCode.NO_SUCH_ENTITY, serviceResult.getCode());
        Assertions.assertEquals("此用户不存在", serviceResult.getMessage());

    }

    @Test
    @Order(4)
    public void testGetUserPageAsList() {
        val param = new QueryUserListParam();
        param.setPageNum(1);
        param.setPageSize(1);
        param.setUsername(username);
        val serviceResult = userService.getUserPageAsList(param);
        Assertions.assertEquals(ServiceResultCode.OK, serviceResult.getCode());
    }


    @Test
    @Order(5)
    public void testGetUserByUsername() {

        val queryUserListParam = new QueryUserListParam();
        queryUserListParam.setUsername(username);
        val serviceResult = userService.getUserPageAsList(queryUserListParam);
        Assertions.assertEquals(ServiceResultCode.OK, serviceResult.getCode());
    }

    @Test
    @Order(6)
    public void testDeleteUser() {
        val param = new DeleteUserParam();
        val user = userService.getUserByUsername(username).getData();
        param.setId(user.getId());
        val serviceResult = userService.delete(param);
        Assertions.assertEquals(ServiceResultCode.OK, serviceResult.getCode());

    }

}
