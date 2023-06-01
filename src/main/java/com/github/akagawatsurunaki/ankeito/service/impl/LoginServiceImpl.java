package com.github.akagawatsurunaki.ankeito.service.impl;

import com.github.akagawatsurunaki.ankeito.api.param.LoginParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.constant.StringValue;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ResultCode;
import com.github.akagawatsurunaki.ankeito.dao.ModelDao;
import com.github.akagawatsurunaki.ankeito.dao.impl.ModelDaoImpl;
import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import com.github.akagawatsurunaki.ankeito.dao.entity.vo.LoginUserVO;
import com.github.akagawatsurunaki.ankeito.service.LoginService;
import com.github.akagawatsurunaki.ankeito.util.Md5Util;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.val;

import java.util.Date;

@NoArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Getter
    private static final LoginService instance = new LoginServiceImpl();

    private static final ModelDao<UserEntity> userDao = new ModelDaoImpl<UserEntity>();

    @Override
    public ServiceResult<LoginUserVO> login(@NonNull LoginParam loginParam) {
        // 用户ID，此时为字符串形式，需要转化
        val userIdStr = loginParam.getUserId();
        // 明文密码
        val password = loginParam.getPassword();

        // 如果用户名或密码为空
        if (userIdStr.isBlank() || password.isBlank()) {
            // 因为没有成功登录，所以获取的用户对象是null, 我们的两个构造函数指出，null的时候第三个参数可以不传
            return ServiceResult.of(ResultCode.LOGIN_FAILED, StringValue.USER_ID_OR_PASSWORD_IS_BLANK);
        }

        // 将字符串转换为ID
        int userId;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            // 此时出现了字符串转换为数字的异常，比如输入了 202211a1，这无法转换
            e.printStackTrace();
            return ServiceResult.of(ResultCode.LOGIN_FAILED, StringValue.ILLEGAL_USER_ID);
        }

        // 调用DAO层获取用户对象
        val loginUser = userDao.findModelById(userId);

        // 如果用户不存在（即为null）
        if (loginUser == null) {
            return ServiceResult.of(ResultCode.LOGIN_FAILED, StringValue.NO_SUCH_USER);
        }

        // 对用户的输入的密码进行加密
        val passwordMd5 = Md5Util.getMd5Password(password);

        // 如果密码不相等
        if (!loginUser.getPassword().equals(passwordMd5)) {
            return ServiceResult.of(ResultCode.LOGIN_FAILED, StringValue.PASSWORD_ERROR);
        }

        val loginUserVO = new LoginUserVO();
        loginUserVO.setUserId(loginUser.getId().toString());
        loginUserVO.setUsername(loginUser.getUsername());
        loginUserVO.setUserType(loginUser.getUserType().value);
        loginUserVO.setLoginTime(new Date().toString());

        return ServiceResult.of(ResultCode.LOGIN_SUCCESSFUL, StringValue.LOGIN_SUCCESSFUL,
                loginUserVO
                );
    }
}
