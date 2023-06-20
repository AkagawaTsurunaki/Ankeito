package com.github.akagawatsurunaki.ankeito.api.param.add;

import lombok.Data;

@Data
public class AddQuestionParam {
    Integer id;
    String qnnreId;
    String content;
    String required;
    String type;
}
