package com.github.akagawatsurunaki.ankeito.service;

import com.github.akagawatsurunaki.ankeito.api.param.LoginParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.entity.User;
import com.github.akagawatsurunaki.ankeito.entity.vo.LoginUserVO;
import lombok.NonNull;

public interface LoginService {

    /**
     * 登录
     *
     * @param loginParam 登录时要传入的参数，包括了账号和密码。
     * @return 包含了当前成功登录的User对象和服务信息
     */
    ServiceResult<LoginUserVO> login(@NonNull LoginParam loginParam);

}
