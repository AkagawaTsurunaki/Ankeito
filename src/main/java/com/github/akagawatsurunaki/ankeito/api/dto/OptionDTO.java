package com.github.akagawatsurunaki.ankeito.api.dto;

import com.github.akagawatsurunaki.ankeito.entity.qnnre.Option;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDTO {
    Option option;
    boolean selected = false;
}
