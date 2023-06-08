package com.github.akagawatsurunaki.ankeito.service;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddProjectParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryProjectListParam;
import com.github.akagawatsurunaki.ankeito.api.result.ServiceResult;
import com.github.akagawatsurunaki.ankeito.common.enumeration.ServiceResultCode;
import com.github.akagawatsurunaki.ankeito.entity.Project;
import com.github.akagawatsurunaki.ankeito.mapper.ProjectMapper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProjectService {

    ProjectMapper projectMapper;

    @Autowired
    ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    /**
     * 查询项目列表
     *
     * @param queryProjectListParam 查询项目列表参数，包括分页参数
     * @return 服务响应结果，包括按照分页查询到的项目列表
     */
    public ServiceResult<List<Project>> getProjectPageAsList(@NonNull QueryProjectListParam queryProjectListParam) {

        val projectPage = projectMapper.selectPage(
                new Page<>(queryProjectListParam.getPageNum(), queryProjectListParam.getPageSize()),
                null
        );
        val records = projectPage.getRecords();
        return ServiceResult.of(
                ServiceResultCode.OK,
                "共查询到" + records.size() + "条项目信息",
                records
        );
    }

    /**
     * 根据项目名称查询
     *
     * @param queryProjectListParam 查询项目列表参数，包括分页参数，要查询的项目名称
     * @return 服务响应结果，包括按照分页、指定项目名称查询到的项目列表
     */
    public ServiceResult<List<Project>> getProjectsByName(@NonNull QueryProjectListParam queryProjectListParam) {
        val projectName = queryProjectListParam.getProjectName();
        if (projectName == null) {
            return ServiceResult.of(
                    ServiceResultCode.ILLEGAL_PARAM,
                    "项目名称不能为空"
            );
        }
        val records = projectMapper.selectPageByProjectName(queryProjectListParam).getRecords();
        return ServiceResult.of(
                ServiceResultCode.OK,
                "共查询到" + records.size() + "条项目信息",
                records
        );
    }

    public ServiceResult<Project> addProject(@NonNull AddProjectParam addProjectParam) {
        val project = Project.builder()
                .id(UUID.fastUUID().toString())
                // TODO: 用户校验
                .userId("TODO")
                .projectName(addProjectParam.getProjectName())
                .projectContent(addProjectParam.getProjectContent())
                .createdBy(addProjectParam.getCreatedBy())
                .lastUpdatedBy(addProjectParam.getLastUpdatedBy())
                .creationDate(new Date())
                .lastUpdateDate(new Date()).build();

        val insert = projectMapper.insert(project);

        if (insert == 1) {
            return ServiceResult.of(
                    ServiceResultCode.OK,
                    "成功增加1条项目信息",
                    project);
        }
        return ServiceResult.of(
                ServiceResultCode.FAILED,
                "增加项目信息失败"
        );
    }

}
