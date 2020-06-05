package com.cccloud.blogs.controller.user.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户信息")
public class AccountDto {

    @ApiModelProperty("用户ID")
    private  Integer  id;

    @ApiModelProperty("用户名")
    private  String  name;

    @ApiModelProperty("邮箱")
    private  String  email;

    @ApiModelProperty("性别 1-男性;2-女性")
    private  Integer  gender;

}
