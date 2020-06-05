package com.cccloud.blogs.controller.user.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "用户信息")
public class AccountDto {

    @ApiModelProperty("用户ID")
    private Long id;

    @ApiModelProperty("账号")
    @NotNull(message = "账号不能为空")
    @Length(max = 32, message = "账号长度异常")
    private String account;

    @ApiModelProperty("用户名")
    private String name;

    @ApiModelProperty("邮箱")
    @Length(max = 128, message = "邮箱长度异常")
    private String email;

    @ApiModelProperty("性别 1-男性;2-女性")
    private Integer gender;


}
