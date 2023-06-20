package com.github.akagawatsurunaki.ankeito.api.param.add;

import lombok.Data;

@Data
public class AddOptionParam {
    String questionId;
    String[] content;
}
