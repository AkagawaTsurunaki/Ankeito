package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import lombok.Data;

@Data
public class Question {
    String id;
    /**
     * 问卷内部标号
     */
    Integer no;
    String content;
    Required required;
    QuestionType type;
}
