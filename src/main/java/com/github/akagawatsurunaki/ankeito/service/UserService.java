package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.mapper.UserMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void getAll() {
        val allUsers = userMapper.selectList(null);


    }

}
