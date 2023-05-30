package com.github.akagawatsurunaki.ankeito;

import com.github.akagawatsurunaki.ankeito.controller.LoginFrameCtrlr;
import com.github.akagawatsurunaki.ankeito.view.LoginFrame;

public class IntellCloudFactoryApplication {
    public static void main(String[] args) {
        LoginFrameCtrlr.getInstance().init();
    }
}
