package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.dto.UserDTO;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.constant.ServiceResultConst;
import com.github.akagawatsurunaki.ankeito.common.convertor.UserConvertor;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.User;
import com.github.akagawatsurunaki.ankeito.mapper.UserMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ServiceResult<List<User>> getAll() {
        val allUsers = userMapper.selectList(null);
        return ServiceResult.of(
                ServiceResultCode.OK,
                "共查询到" + allUsers.size() + "条用户信息",
                allUsers);
    }

    public ServiceResult<User> add(@NonNull UserDTO userDTO) {
        val user = new UserConvertor(userDTO).dtoToEntity();
        if (user == null) {
            return new ServiceResultConst<User>().DTO_TO_ENTITY_EXCEPTION_SERVICE_RESULT;
        }
        val insert = userMapper.insert(user);
        if (insert == 1) {
            return ServiceResult.of(
                    ServiceResultCode.OK,
                    "成功增加1条用户信息",
                    user);
        }
        return ServiceResult.of(
                ServiceResultCode.FAILED,
                "增加用户信息失败"
        );
    }

    public ServiceResult<User> modify(@NonNull UserDTO userDTO) {
        val user = new UserConvertor(userDTO).dtoToEntity();
        if (user == null) {
            return new ServiceResultConst<User>().DTO_TO_ENTITY_EXCEPTION_SERVICE_RESULT;
        }

        val oldUser = userMapper.selectById(user.getId());
        if (oldUser == null) {
            return ServiceResult.of(
                    ServiceResultCode.NO_SUCH_ENTITY,
                    "此用户不存在",
                    user
            );
        }

        val update = userMapper.updateById(user);
        if (update == 1) {
            return ServiceResult.of(
                    ServiceResultCode.OK,
                    "成功修改1条用户信息",
                    user);
        }

        return ServiceResult.of(
                ServiceResultCode.FAILED,
                "修改用户信息失败"
        );

    }

}
