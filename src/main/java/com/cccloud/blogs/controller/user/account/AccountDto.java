package com.cccloud.blogs.controller.user.account;

import com.cccloud.blogs.entity.user.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

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

    @ApiModelProperty("用户状态 1-正常;2-禁用")
    @NotNull
    private Integer state;

    @ApiModelProperty("性别 1-男性;2-女性")
    private Integer gender;


    public static AccountDto convert(Account src) {
        AccountDto tar = new AccountDto();
        BeanUtils.copyProperties(src, tar);
        return tar;
    }
}
