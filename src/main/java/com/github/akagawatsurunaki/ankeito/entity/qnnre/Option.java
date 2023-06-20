package com.github.akagawatsurunaki.ankeito.entity.qnnre;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("`option`")
public class Option {
    Integer id;
    String questionId;
    String content;
}
