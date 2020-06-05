package com.cccloud.blogs.controller.user.account;

import com.cccloud.blogs.easybatis.anno.condition.filter.Equal;
import com.cccloud.blogs.easybatis.anno.condition.filter.Like;
import com.cccloud.blogs.easybatis.interfaces.Page;
import com.cccloud.blogs.easybatis.interfaces.impl.DefaultPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "用户查询")
public class AccountFilter extends DefaultPage {

    @ApiModelProperty("用户ID")
    @Equal
    private  Integer  id;

    @ApiModelProperty("用户名")
    @Like
    private  String  name;

    @ApiModelProperty("邮箱")
    @Like
    private  String  email;


}
