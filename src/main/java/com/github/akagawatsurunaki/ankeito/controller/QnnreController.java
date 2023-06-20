package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddQnnreParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQuestionParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryQnnreListParam;
import com.github.akagawatsurunaki.ankeito.api.result.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.common.convertor.ServiceResultConvertor;
import com.github.akagawatsurunaki.ankeito.service.QnnreService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(path = "/addQnnre", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addQnnre(@RequestBody AddQnnreParam addQnnreParam) {
        val serviceResult = qnnreService.addQnnre(addQnnreParam);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/getQnnre", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity getQnnre(@RequestBody QueryQnnreListParam queryQnnreListParam) {
        val serviceResult = qnnreService.getQnnre(queryQnnreListParam);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }

    @RequestMapping(path = "/addMcq", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity addMultipleChoiceQuestion(@RequestBody AddQuestionParam queryQnnreListParam) {
        val serviceResult = qnnreService.addMultipleChoiceQuestion(queryQnnreListParam);
        return new ServiceResultConvertor<>(serviceResult).toHttpResponseEntity();
    }
}
