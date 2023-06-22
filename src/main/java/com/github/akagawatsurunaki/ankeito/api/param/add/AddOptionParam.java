package com.github.akagawatsurunaki.ankeito.api.param.add;

import lombok.Data;

@Data
public class AddOptionParam {
    Integer questionId;
    String qnnreId;
    String[] content;
}
