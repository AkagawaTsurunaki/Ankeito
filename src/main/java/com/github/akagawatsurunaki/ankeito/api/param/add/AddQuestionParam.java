package com.github.akagawatsurunaki.ankeito.api.param.add;

import lombok.Data;

@Data
public class AddQuestionParam {
    Integer no;
    String content;
    String required;
    String type;
}
