package com.github.akagawatsurunaki.ankeito.api.param.modify;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddOptionParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQuestionParam;
import lombok.Data;

import java.util.List;

@Data
public class ModifyQnnreParam {
    String qnnreId;
    String qnnreTitle;
    String qnnreDescription;

    List<AddQuestionParam> addQuestionParams;

    List<AddOptionParam> addOptionParams;

}
