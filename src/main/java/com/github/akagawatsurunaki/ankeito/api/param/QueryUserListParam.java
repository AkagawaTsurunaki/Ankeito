package com.github.akagawatsurunaki.ankeito.api.param;

import lombok.Data;

@Data
public class QueryUserListParam {
    int pageNum;
    int pageSize;
    String userName;
}
