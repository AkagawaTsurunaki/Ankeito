package com.github.akagawatsurunaki.ankeito.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.akagawatsurunaki.ankeito.entity.Project;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {
}
