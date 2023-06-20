package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Qnnre;
import lombok.Data;

import java.util.List;

@Data
public class QnnreDTO {
    Qnnre qnnre;

    List<QuestionDTO> questionDTOList;

}
