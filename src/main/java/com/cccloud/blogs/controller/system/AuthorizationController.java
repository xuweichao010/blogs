package com.cccloud.blogs.controller.system;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.controller.system.authorization.LoginDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/authorization")
@Api(tags = "SYS_用户登录")
public class AuthorizationController {
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonMessage<Void> login(LoginDto login) {
        return JsonMessage.succeed();
    }

    @DeleteMapping("/logout")
    @ApiOperation("用户退出")
    public JsonMessage<Void> logout() {
        return JsonMessage.succeed();
    }

    @GetMapping("/rsa/publicKey")
    public JsonMessage<String> rsaPublicKey(@ApiIgnore HttpSession session) {
        session.setAttribute("publicKey", "publicKey");
        return JsonMessage.succeed("publicKey");
    }

}
