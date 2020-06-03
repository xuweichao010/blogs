package com.cccloud.blogs.controller.system;

import com.cccloud.blogs.commons.JsonMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/open/access")
@Api(tags = "sys_access: 统计系统访问")
public class AccessController {

    @GetMapping("/")
    @ApiOperation("记录系统访问次数")
    public JsonMessage<Void> access(@ApiIgnore HttpSession session) {
        String  userAccess = (String) session.getAttribute("userAccess");
        if(userAccess == null){
            session.setAttribute("userAccess","用户访问了");
            System.out.println("用户访问了");
        }
        return JsonMessage.succeed();
    }
}
