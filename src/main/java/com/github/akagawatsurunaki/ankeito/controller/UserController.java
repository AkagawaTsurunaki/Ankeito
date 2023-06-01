package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.dao.entity.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserEntity userEntity) {
        val httpResponseEntity = new HttpResponseEntity();
        try {
            val hasUser = userService.selectUserInfo(userEntity);

            if (CollectionUtils.isEmpty(hasUser)) {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(hasUser.get(0));
                httpResponseEntity.setMessage("用户名或者密码错误");
            } else {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(hasUser);
                httpResponseEntity.setMessage("登录成功");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUser(@RequestBody UserEntity userEntity) {
        val httpResponseEntity = new HttpResponseEntity();
        try {
            val result = userService.addUserInfo(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("666");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("创建成功");

            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("创建失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUser(@RequestBody UserEntity userEntity) {
        val httpResponseEntity = new HttpResponseEntity();
        try {
            val result = userService.modifyUserInfo(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("10");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("修改成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("修改失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUser(@RequestBody UserEntity userEntity) {
        val httpResponseEntity = new HttpResponseEntity();
        try {
            val result = userService.deleteUserById(userEntity);

            if (result != 0) {
                httpResponseEntity.setCode("10");
                httpResponseEntity.setData(result);
                httpResponseEntity.setMessage("删除成功");
            } else {
                httpResponseEntity.setCode("0");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("删除失败");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
