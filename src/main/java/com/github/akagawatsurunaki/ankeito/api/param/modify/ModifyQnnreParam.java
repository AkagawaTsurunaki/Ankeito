package com.github.akagawatsurunaki.ankeito.api.param.modify;

import com.github.akagawatsurunaki.ankeito.api.param.add.AddOptionParam;
import com.github.akagawatsurunaki.ankeito.api.param.add.AddQuestionParam;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import lombok.Data;

import java.util.List;

@Data
public class ModifyQnnreParam {
    String qnnreName;
    String qnnreDescription;

    List<AddQuestionParam> addQuestionParams;

    List<AddOptionParam> addOptionParams;

}
