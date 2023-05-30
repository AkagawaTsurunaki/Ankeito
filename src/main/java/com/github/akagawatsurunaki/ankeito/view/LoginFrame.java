package com.github.akagawatsurunaki.ankeito.view;

import com.github.akagawatsurunaki.ankeito.controller.LoginFrameCtrlr;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    @Getter
    private JTextField usernameField;
    @Getter
    private JPasswordField passwordField;

    public LoginFrame() {
        initComponents();
    }

    private void initComponents() {
        // 设置窗口标题
        setTitle("登录");

        // 设置窗口大小
        setSize(300, 200);

        // 设置窗口居中显示
        setLocationRelativeTo(null);

        // 设置窗口关闭操作
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建面板
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));
        getContentPane().add(panel, BorderLayout.CENTER);

        // 创建账号标签和输入框
        JLabel usernameLabel = new JLabel("账号：");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);

        // 创建密码标签和输入框
        JLabel passwordLabel = new JLabel("密码：");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        // 创建登录按钮
        JButton loginButton = new JButton("登录");
        // 注册点击按钮后登录本系统的事件
        loginButton.addActionListener(e -> LoginFrameCtrlr.getInstance().login());

        // 创建取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(e -> {
            dispose();
        });

        // 创建按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }
}
