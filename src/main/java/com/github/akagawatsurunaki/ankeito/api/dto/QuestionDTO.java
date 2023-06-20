package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    Question question;
    List<Option> optionList;
}
