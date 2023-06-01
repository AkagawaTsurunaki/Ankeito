package com.github.akagawatsurunaki.ankeito.controller;

import com.github.akagawatsurunaki.ankeito.dao.entity.HttpResponseEntity;
import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import lombok.NonNull;

public class UserController {

    /**
     * 用户列表
     * @param userEntity
     * @return
     */
    HttpResponseEntity queryUserList(@NonNull UserEntity userEntity) {
        return null;
    }

    /**
     * 新增用户
     * @param userEntity
     * @return
     */
    HttpResponseEntity addUserInfo(@NonNull UserEntity userEntity) {
        return null;
    }

    /**
     * 修改用户
     * @param userEntity
     * @return
     */
    HttpResponseEntity modifyUserInfo(@NonNull UserEntity userEntity) {
        return null;
    }

    /**
     * 删除用户信息
     * @param userEntity
     * @return
     */
    HttpResponseEntity deleteUserById(@NonNull UserEntity userEntity) {
        return null;
    }
}
