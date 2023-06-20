package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDTO {
    Question question;
    List<Option> optionList;
}
