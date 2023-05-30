package com.github.akagawatsurunaki.ankeito.dao;

import jakarta.validation.constraints.NotEmpty;
import lombok.NonNull;

import java.util.Collection;
import java.util.List;

public interface ModelDao<Model> {

    /**
     * 获取所有Model
     *
     * @return 包括所有的Model组成的List
     * @Note 这里Model是泛型，可以是User、Factory等实体类
     */
    List<Model> findAllModels();

    /**
     * 通过ID查找指定的Model
     *
     * @return 指定的Model
     * @Note ID必须是对于这个Model是唯一确定的。比如一个身份证号唯一确定一个User。
     */
    Model findModelById(@NonNull Integer id);

    /**
     * 添加若干个Model
     *
     * @return 成功添加了多少个Model，是一个int
     */
    int addModels(
            @NonNull @NotEmpty(message = "添加的List<Model>不能为empty")
            Collection<Model> modelCollection
    );

    // TODO: 或许还有增删改查的更多方法需要在这里写

}
