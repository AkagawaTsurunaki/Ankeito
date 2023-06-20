package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.common.convertor.ServiceResultConvertor;
import com.github.akagawatsurunaki.ankeito.service.QnnreService;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QnnreController {
    private final QnnreService qnnreService;

    @Autowired
    public QnnreController(QnnreService qnnreService) {
        this.qnnreService = qnnreService;
    }

    @RequestMapping(path = "/queryUserRole", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryUserRole() {
        val serviceResult = qnnreService.getQnnreType();
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

}
