package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Qnnre {
    String id;
    String projectId;
    String name;
    String description;
    Date startTime;
    Date stopTime;
}
