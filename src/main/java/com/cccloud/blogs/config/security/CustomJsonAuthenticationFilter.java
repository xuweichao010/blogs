package com.cccloud.blogs.config.security;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 自定义JSON认证过滤器
 *
 * @version 0.6.0-SNAPSHOT
 */
@Component
public class CustomJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String[] passwordArray = request.getParameterMap().get(this.getPasswordParameter());
        if (passwordArray == null || passwordArray.length == 0) return null;
        return passwordArray[0];
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String[] usernameArray = request.getParameterMap().get(this.getUsernameParameter());
        if (usernameArray == null || usernameArray.length == 0) return null;
        return usernameArray[0];
    }
}
