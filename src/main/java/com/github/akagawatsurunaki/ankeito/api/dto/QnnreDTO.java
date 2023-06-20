package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QnnreDTO {
    Qnnre qnnre;

    List<QuestionDTO> questionDTOList;

}
