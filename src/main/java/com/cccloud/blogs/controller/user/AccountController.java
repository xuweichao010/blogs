package com.cccloud.blogs.controller.user;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.controller.user.account.AccountAddDto;
import com.cccloud.blogs.controller.user.account.AccountDto;
import com.cccloud.blogs.controller.user.account.AccountFilter;
import com.cccloud.blogs.entity.user.Account;
import com.cccloud.blogs.service.user.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@Api(tags = "USER_账号管理")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    @ApiOperation("添加账号")
    public JsonMessage<Void> add(@Validated @RequestBody AccountAddDto dto) {
        dto.setId(null);
        Account account = Account.convert(dto);
        accountService.encoderPassword(account, null);
        accountService.add(account);
        return JsonMessage.succeed();
    }

    @PutMapping("/{id}")
    @ApiOperation("修改账号")
    public JsonMessage<Void> update(@ApiParam("用户ID") @PathVariable("id") Long id,
                                    @ApiParam("修改信息") @RequestBody AccountDto dto) {
        dto.setId(id);
        Account account = Account.convert(dto);
        accountService.update(account);
        return JsonMessage.succeed();
    }

    @GetMapping("/{id}")
    @ApiOperation("ID查询账号")
    public JsonMessage<AccountDto> get(@ApiParam("用户ID") @PathVariable("id") Long id) {
        return JsonMessage.succeed(AccountDto.convert(accountService.getBy(id)));
    }

    @GetMapping("")
    @ApiOperation("条件查询账号")
    public JsonMessage<List<AccountDto>> list(@ApiParam("查询条件") AccountFilter filter) {
        return JsonMessage.succeed();
    }


}
