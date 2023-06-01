package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.common.utils.UUIDUtil;
import com.github.akagawatsurunaki.ankeito.dao.UserEntityMapper;
import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * 登录
     * @param userEntity
     * @return
     */
    public List<UserEntity> selectUserInfo(UserEntity userEntity) {
        return userEntityMapper.selectUserInfo(userEntity);
    }

    /**
     * 查询用户列表
     * @param userEntity
     * @return
     */
    public List<UserEntity> queryUserList(UserEntity userEntity) {
        return userEntityMapper.queryUserList(userEntity);
    }

    /**
     * 创建用户
     * @param userEntity
     * @return
     */
    public int addUserInfo(UserEntity userEntity) {
        userEntity.setId(UUIDUtil.getOneUUID());
        val userResult = userEntityMapper.insert(userEntity);
        if (userResult != 0) {
            return 3;
        } else {
            return userResult;
        }
    }

    public int modifyUserInfo(UserEntity userEntity) {
        return userEntityMapper.updateByPrimaryKeySelective(userEntity);
    }

    public int deleteUserById(UserEntity userEntity) {
        return userEntityMapper.deleteUserById(userEntity);
    }

}
