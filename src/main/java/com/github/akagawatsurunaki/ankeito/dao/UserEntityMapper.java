package com.github.akagawatsurunaki.ankeito.dao;

import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserEntityMapper {
    UserEntity selectByPrimaryKey(@NonNull String id);
    UserEntity deleteByPrimaryKey(@NonNull String id);
    int insert(@NonNull UserEntity userEntity);
    int insertSelective(@NonNull UserEntity userEntity);
    int updateByPrimaryKeySelective(@NonNull UserEntity userEntity);
    int updateByPrimaryKey(@NonNull UserEntity userEntity);

}
