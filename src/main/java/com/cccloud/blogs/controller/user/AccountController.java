package com.cccloud.blogs.controller.user;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.controller.user.account.AccountDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api(tags = "user_account: 系统账号管理")
public class AccountController {

    @PostMapping("")
    public JsonMessage<Void> add(@RequestBody AccountDto dto) {
        return JsonMessage.succeed();
    }
}
