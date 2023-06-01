package com.github.akagawatsurunaki.ankeito.service.impl;

import com.github.akagawatsurunaki.ankeito.dao.UserEntityMapper;
import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import com.github.akagawatsurunaki.ankeito.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserEntityMapper userEntityMapper;

    @Override
    public int addUserInfo(@NonNull UserEntity userEntity, @NonNull String username) {
        userEntityMapper.insert(userEntity);

        return 0;
    }

    @Override
    public int modifyUserInfo(@NonNull UserEntity userEntity, @NonNull String username) {
        return 0;
    }

    @Override
    public int deleteUserById(@NonNull UserEntity userEntity) {
        return 0;
    }

    @Override
    public List<Object> queryUserList(@NonNull UserEntity userEntity) {
        return null;
    }
}
