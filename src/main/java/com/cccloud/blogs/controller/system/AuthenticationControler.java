package com.cccloud.blogs.controller.system;

import com.cccloud.blogs.commons.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@Api(tags = "SYS_用户信息")
public class AuthenticationControler {

    @GetMapping("")
    @ApiOperation("获取用户登录信息")
    public JsonMessage<Authentication> get() {
        return JsonMessage.succeed(SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/role")
    @ApiOperation("获取用户角色")
    public JsonMessage<Authentication> roleList() {
        return JsonMessage.succeed(SecurityContextHolder.getContext().getAuthentication());
    }
}
