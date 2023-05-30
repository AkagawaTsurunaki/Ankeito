package com.github.akagawatsurunaki.ankeito.api.param;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginParam {

    String userId;

    String password;

}
