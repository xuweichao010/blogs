package com.cccloud.blogs.controller.system.authorization;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "LoginDto",description = "用户登录")
public class LoginDto {
    private String username;
    private String password;
}
