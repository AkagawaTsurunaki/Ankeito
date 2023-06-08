package com.github.akagawatsurunaki.ankeito.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Project {
    String id;
    String personInCharge;
    String projectName;
    String projectContent;
    String createdBy;
    String lastUpdatedBy;
    Date createTime;
    Date lastUpdateDate;
}
