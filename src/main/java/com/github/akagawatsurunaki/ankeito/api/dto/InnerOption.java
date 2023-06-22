package com.github.akagawatsurunaki.ankeito.api.dto;

import lombok.Builder;

@Builder
public class InnerOption {
    Integer optionId;
    String optionContent;
    Float percent;
    Integer count;
}
