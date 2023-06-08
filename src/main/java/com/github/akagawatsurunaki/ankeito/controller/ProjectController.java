package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.param.QueryProjectListParam;
import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.common.convertor.ServiceResultConvertor;
import com.github.akagawatsurunaki.ankeito.service.ProjectService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    ProjectService projectService;

    @Autowired
    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(path = "/userLogin", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity queryProjectList(@Validated @RequestBody QueryProjectListParam queryProjectListParam) {
        val serviceResult = projectService.getPageAsList(queryProjectListParam);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }


}
