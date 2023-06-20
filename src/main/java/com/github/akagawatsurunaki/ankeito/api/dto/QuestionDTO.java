package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Item;
import com.github.akagawatsurunaki.ankeito.entity.qnnre.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    Question question;
    List<Item> itemList;
}
