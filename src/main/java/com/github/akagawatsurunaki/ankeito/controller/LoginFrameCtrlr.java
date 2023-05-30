package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.api.param.LoginParam;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ResultCode;
import com.github.akagawatsurunaki.ankeito.service.LoginService;
import com.github.akagawatsurunaki.ankeito.service.impl.LoginServiceImpl;
import com.github.akagawatsurunaki.ankeito.view.LoginFrame;
import lombok.Getter;
import lombok.val;

import java.util.Arrays;

public final class LoginFrameCtrlr {

    @Getter
    private static final LoginFrameCtrlr instance = new LoginFrameCtrlr();

    private static final LoginService LOGIN_SERVICE = LoginServiceImpl.getInstance();

    private LoginFrame loginFrame;

    public void init() {
        if (loginFrame == null) {
            loginFrame = new LoginFrame();
        }
        loginFrame.setVisible(true);
    }

    // 例如登录后要跳转到某个页面
    public void login() {

        if (loginFrame == null) {
            throw new NullPointerException("登录Frame未被初始化，应该首先调用LoginFrameCtrlr.init()方法");
        }

        // 从组件上获取登录信息
        val username = loginFrame.getUsernameField().getText();
        val password = Arrays.toString(loginFrame.getPasswordField().getPassword());

        val loginParam = LoginParam.builder()
                .userId(username)
                .password(password)
                .build();

        val loginServiceResult = LOGIN_SERVICE.login(loginParam);

        if (loginServiceResult.getResultCode().equals(ResultCode.LOGIN_FAILED)) {
            val message = loginServiceResult.getMessage();
            // TODO: 在Frame里面修改组件的信息
            // loginFrame.getXXXXText().setText(message);
        } else {
            // TODO: 跳转至某个页面，可能需要调用其它Controller
        }

    }

}
