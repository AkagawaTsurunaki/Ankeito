package com.github.akagawatsurunaki.ankeito.dao;

import com.github.akagawatsurunaki.ankeito.dao.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public interface UserEntityMapper {

    /**
     * 搜索用户
     * @param userEntity
     * @return
     */
    List<Map<String, Object>> queryUserList(@NonNull UserEntity userEntity);

    /**
     * 增加数据
     * @param userEntity
     * @return
     */
    int insert(@NonNull UserEntity userEntity);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int deleteUserId(@NonNull @NotEmpty(message = "用户ID不能为空") String id);

    /**
     * 修改数据
     * @param userEntity
     * @return
     */
    int updateByPrimaryKeySelective(@NonNull UserEntity userEntity);

}
