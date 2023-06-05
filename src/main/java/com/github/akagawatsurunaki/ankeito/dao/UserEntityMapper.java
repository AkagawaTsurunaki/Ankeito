package com.github.akagawatsurunaki.ankeito.dao;

import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserEntityMapper {
    UserEntity selectByPrimaryKey(@NonNull String id);

    UserEntity deleteByPrimaryKey(@NonNull String id);

    int insert(@NonNull UserEntity userEntity);

    int insertSelective(@NonNull UserEntity userEntity);

    int updateByPrimaryKeySelective(@NonNull UserEntity userEntity);

    int updateByPrimaryKey(@NonNull UserEntity userEntity);

    List<UserEntity> queryUserList(UserEntity userEntity);

    int deleteUserById(UserEntity userEntity);

    int deleteUserByName(UserEntity userEntity);

    List<UserEntity> selectUserInfo(UserEntity userEntity);

}
