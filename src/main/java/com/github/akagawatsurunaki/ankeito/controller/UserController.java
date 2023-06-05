package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.dto.UserDTO;
import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.common.convertor.ServiceResultConvertor;
import com.github.akagawatsurunaki.ankeito.entity.User;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity userLogin(@RequestBody UserDTO userDTO) {
        val serviceResult = userService.userLogin(userDTO);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/addUserInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addUserInfo(@RequestBody UserDTO userDTO) {
        val serviceResult = userService.add(userDTO);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/modifyUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity modifyUser(@RequestBody UserDTO userDTO) {
        val serviceResult = userService.modify(userDTO);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/deleteUser", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity deleteUser(@RequestBody UserDTO userDTO) {
        val serviceResult = userService.delete(userDTO);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/queryUserList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserList(@RequestBody UserDTO userDTO) {
        val serviceResult = userService.getAll();
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

}
