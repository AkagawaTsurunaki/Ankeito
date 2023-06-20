package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import lombok.Data;

@Data
public class Question {
    String id;
    String content;
    QuestionType type;
}
