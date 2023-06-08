package com.github.akagawatsurunaki.ankeito.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.akagawatsurunaki.ankeito.api.param.QueryProjectListParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.Project;
import com.github.akagawatsurunaki.ankeito.mapper.ProjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class ProjectService {

    ProjectMapper projectMapper;

    @Autowired
    ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public ServiceResult<List<Project>> getPageAsList(@NonNull QueryProjectListParam queryProjectListParam) {

        val page = projectMapper.selectPage(
                new Page<>(queryProjectListParam.getPageNum(), queryProjectListParam.getPageSize()),
                null
        );
        val records = page.getRecords();
        return ServiceResult.of(
                ServiceResultCode.OK,
                "共查询到" + records.size() + "条项目信息",
                records
        );
    }

}
