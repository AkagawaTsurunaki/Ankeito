package com.github.akagawatsurunaki.ankeito.entity.answer;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseSheetDetail {

    /**
     * 答卷ID(主键)
     */
    @TableId(type = IdType.INPUT)
    String responseSheetId;

    /**
     * 问卷ID
     */
    String qnnreId;
    /**
     * 问题ID 为了防止有其他类型的问题
     */
    String questionId;

}
