package com.github.akagawatsurunaki.ankeito.api.param;

import lombok.Data;

import java.util.Date;

@Data
public class AddUserParam {
    String username;
    String password;
    Date startTime;
    Date stopTime;
}
