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
    Date creationDate;
    Date lastUpdateDate;
}
