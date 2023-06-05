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

    /**
     * 获取所有用户列表
     *
     * @return 服务响应结果
     */
    public ServiceResult<List<User>> getAll() {
        val allUsers = userMapper.selectList(null);
        return ServiceResult.of(
                ServiceResultCode.OK,
                "共查询到" + allUsers.size() + "条用户信息",
                allUsers);
    }

    public ServiceResult<User> getUserById(@NonNull UserDTO userDTO) {
        val user = userMapper.selectById(userDTO.getId());
        if (user == null) {
            return ServiceResult.of(
                    ServiceResultCode.NO_SUCH_ENTITY,
                    "此用户不存在");
        }
        return ServiceResult.of(
                ServiceResultCode.OK,
                "查询到1条用户信息",
                user
        );
    }

    public ServiceResult<User> userLogin(@NonNull UserDTO userDTO) {
        val user = userMapper.selectById(userDTO.getId());

        if (user == null || (!user.getPassword().equals(userDTO.getPassword()))) {
            return ServiceResult.of(
                    ServiceResultCode.FAILED,
                    "用户名或密码错误"
            );
        }

        return ServiceResult.of(
                ServiceResultCode.OK,
                "登录成功",
                user
        );
    }

    /**
     * 增加一条用户信息
     *
     * @param userDTO 用户数据传输对象
     * @return 服务响应结果
     */
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

    /**
     * 修改一条用户信息
     *
     * @param userDTO 用户数据传输对象
     * @return 服务响应结果
     */
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

    /**
     * 删除一条用户信息
     *
     * @param userDTO 用户数据传输对象
     * @return 服务响应结果
     */
    public ServiceResult<User> delete(@NonNull UserDTO userDTO) {
        val id = userDTO.getId();
        val user = userMapper.selectById(id);
        if (user == null) {
            return ServiceResult.of(
                    ServiceResultCode.NO_SUCH_ENTITY,
                    "此用户不存在");
        }

        val delete = userMapper.deleteById(id);
        if (delete == 1) {
            return ServiceResult.of(
                    ServiceResultCode.OK,
                    "成功删除1条用户信息",
                    user);
        }
        return ServiceResult.of(
                ServiceResultCode.FAILED,
                "删除用户信息失败"
        );
    }

}
