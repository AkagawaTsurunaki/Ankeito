package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    Integer id;
    String qnnrId;
    /**
     * 问卷内部标号
     */
    String content;
    Required required;
    QuestionType type;
}
