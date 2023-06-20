package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.github.akagawatsurunaki.ankeito.common.enumeration.QuestionType;
import com.github.akagawatsurunaki.ankeito.common.enumeration.Required;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@FieldNameConstants
public class Question {
    Integer id;
    String qnnreId;
    String content;
    Required required;
    QuestionType type;
}
