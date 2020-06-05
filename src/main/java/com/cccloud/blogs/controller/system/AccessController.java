package com.cccloud.blogs.controller.system;

import com.cccloud.blogs.commons.JsonMessage;
import com.cccloud.blogs.service.system.AccessService;
import com.cccloud.blogs.utils.HttpUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
@RequestMapping("/open/access")
@Api(tags = "sys_访问统计")
public class AccessController {
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private AccessService accessService;


    @GetMapping("/")
    @ApiOperation("记录系统访问次数")
    public JsonMessage<Void> access(@ApiIgnore HttpSession session, @ApiIgnore HttpServletRequest request) {
        String userAccess = (String) session.getAttribute("userAccess");
        if (userAccess == null) {
            String ip = HttpUtils.requestIp(request);
            threadPoolExecutor.submit(() -> {
                accessService.anonymous(ip);
            });
        }
        return JsonMessage.succeed();
    }
}
