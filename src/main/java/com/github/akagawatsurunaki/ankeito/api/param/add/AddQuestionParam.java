package com.github.akagawatsurunaki.ankeito.api.param.add;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddQuestionParam {
    String qnnreId;
    Integer index;
    String problemName;
    Boolean mustAnswer;
    String type;
}
