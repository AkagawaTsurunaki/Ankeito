package com.github.akagawatsurunaki.ankeito.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseOption {
    @TableId(type = IdType.INPUT)
    String responseSheetId;
    /**
     * id 和 questionId是选项的主键
     */
    Integer optionId;
    String questionId;
    String qnnreId;
}
