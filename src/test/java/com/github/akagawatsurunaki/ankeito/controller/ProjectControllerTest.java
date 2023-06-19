package com.github.akagawatsurunaki.ankeito.controller;

import cn.hutool.core.util.RandomUtil;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddProjectParam;
import com.github.akagawatsurunaki.ankeito.api.param.delete.DeleteProjectParam;
import com.github.akagawatsurunaki.ankeito.api.param.modify.ModifyProjectParam;
import com.github.akagawatsurunaki.ankeito.api.param.query.QueryProjectListParam;
import com.github.akagawatsurunaki.ankeito.entity.Project;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ProjectControllerTest {

    private String projectName;
    private String projectContent;
    private String createdBy;
    private String lastUpdatedBy;

    @Autowired
    private ProjectController projectController;

    private void init() {
        projectName = RandomUtil.randomString(RandomUtil.BASE_CHAR, 16);
        projectContent = RandomUtil.randomString(RandomUtil.BASE_CHAR, 200);
        createdBy = RandomUtil.randomString(RandomUtil.BASE_CHAR, 8);
        lastUpdatedBy = RandomUtil.randomString(RandomUtil.BASE_CHAR, 8);
    }

    @Test
    public void testAddProjectInfo() {
        init();
        val addProjectParam = new AddProjectParam();
        addProjectParam.setProjectName(projectName);
        addProjectParam.setProjectContent(projectContent);
        addProjectParam.setLastUpdatedBy(lastUpdatedBy);
        addProjectParam.setCreatedBy(createdBy);
        projectController.addProjectInfo(addProjectParam);
    }

    @Test
    public void testModify() {
        val queryProjectListParam = new QueryProjectListParam();
        queryProjectListParam.setProjectName(projectName);
        val httpResponseEntity = projectController.queryProjectList(queryProjectListParam);
        val data = (List<Project>) httpResponseEntity.getData();
        if (data != null && !data.isEmpty()) {
            val project = data.get(0);
            val modifyProjectParam = new ModifyProjectParam();
            modifyProjectParam.setId(project.getId());
            modifyProjectParam.setProjectContent(RandomUtil.randomString(RandomUtil.BASE_CHAR, 200));
            modifyProjectParam.setProjectName(projectName);
            projectController.modifyProjectInfo(modifyProjectParam);
        }
    }


    @Test
    public void testFindAndModify() {
        var httpResponseEntity = projectController.queryProjectList(new QueryProjectListParam());

        val queryProjectListParam = new QueryProjectListParam();
        queryProjectListParam.setProjectName(projectName);
        httpResponseEntity = projectController.queryProjectList(queryProjectListParam);
        val projects = ((List<Project>) httpResponseEntity.getData());
        val project = projects.get(0);

        projectController.queryProjectList(new QueryProjectListParam());

        val modifyProjectParam = new ModifyProjectParam();
        modifyProjectParam.setId(modifyProjectParam.getId());
        modifyProjectParam.setProjectName(projectName);
        modifyProjectParam.setProjectContent(RandomUtil.randomString(RandomUtil.BASE_CHAR, 200));
        projectController.modifyProjectInfo(modifyProjectParam);
    }

    @Test
    public void testDelete() {
        val queryProjectListParam = new QueryProjectListParam();
        queryProjectListParam.setProjectName(projectName);
        val httpResponseEntity = projectController.queryProjectList(queryProjectListParam);
        val projects = ((List<Project>) httpResponseEntity.getData());
        val project = projects.get(0);

        projectController.deleteProjectById(new DeleteProjectParam());

        val deleteProjectParam = new DeleteProjectParam();
        deleteProjectParam.setId(project.getId());

        projectController.deleteProjectById(deleteProjectParam);
    }
}
