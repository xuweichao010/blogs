package com.cccloud.blogs.controller.user;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.controller.user.account.AccountDto;
import com.cccloud.blogs.controller.user.account.AccountFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "user_account: 系统账号管理")
public class AccountController {

    @PostMapping("")
    @ApiOperation("添加账号")
    public JsonMessage<Void> add(@RequestBody AccountDto dto) {
        return JsonMessage.succeed();
    }

    @PutMapping("/{id}")
    @ApiOperation("修改账号")
    public JsonMessage<Void> update(@ApiParam("用户ID") @PathVariable("id") Integer id,
                                    @ApiParam("修改信息") @RequestBody AccountDto dto) {
        return JsonMessage.succeed();
    }

    @GetMapping("/{id}")
    @ApiOperation("ID查询账号")
    public JsonMessage<AccountDto> get(@ApiParam("用户ID") @PathVariable("id") Integer id) {
        return JsonMessage.succeed();
    }

    @GetMapping("")
    @ApiOperation("条件查询账号")
    public JsonMessage<List<AccountDto>> list(@ApiParam("查询条件") AccountFilter filter) {
        return JsonMessage.succeed();
    }


}
