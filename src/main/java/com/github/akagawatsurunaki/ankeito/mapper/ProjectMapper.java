package com.github.akagawatsurunaki.ankeito.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryProjectListParam;
import com.github.akagawatsurunaki.ankeito.entity.Project;
import com.github.akagawatsurunaki.ankeito.entity.User;
import lombok.val;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    default Page<Project> selectPageByProjectName(@NonNull QueryProjectListParam queryProjectListParam) {
        val projectName = queryProjectListParam.getProjectName();
        val wrapper = new QueryWrapper<Project>().eq("project_name", projectName);
        return this.selectPage(
                new Page<>(queryProjectListParam.getPageNum(), queryProjectListParam.getPageSize()),
                wrapper);
    }

}