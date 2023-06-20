package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.common.convertor.ServiceResultConvertor;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QnnreController {
    @Autowired
    public QnnreController(UserService userService) {
        this.userService = userService;
    }

    public UserService userService;

    @RequestMapping(path = "/queryUserRole", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserRole() {
        val serviceResult = userService.getUserRole();
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

}
