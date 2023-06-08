package com.github.akagawatsurunaki.ankeito.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Project {
    String id;
    String userId;
    String projectName;
    String projectContent;
    String createdBy;
    String creationDate;
    Date lastUpdateDate;
}
