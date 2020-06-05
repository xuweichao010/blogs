package com.cccloud.blogs.controller.user.account;

import com.cccloud.blogs.easybatis.anno.condition.filter.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户注册信息")
public class AccountAddDto extends AccountDto {

    @ApiModelProperty("密码")
    @NotNull
    private String password;
}
