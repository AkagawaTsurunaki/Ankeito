package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.entity.UserEntity;
import lombok.NonNull;

import java.util.List;

public interface UserService {

    /**
     * 增加用户
     * @param userEntity
     * @param username
     * @return
     */
    int addUserInfo(@NonNull UserEntity userEntity, @NonNull String username);

    /**
     * 修改用户
     * @param userEntity
     * @param username
     * @return
     */
    int modifyUserInfo(@NonNull UserEntity userEntity, @NonNull String username);

    /**
     * 删除用户
     * @param userEntity
     * @return
     */
    int deleteUserById(@NonNull UserEntity userEntity);

    /**
     * 查询用户列表
     * @param userEntity
     * @return
     */
    List<Object> queryUserList(@NonNull UserEntity userEntity);

}
