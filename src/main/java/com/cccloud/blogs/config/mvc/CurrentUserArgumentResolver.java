package com.cccloud.blogs.config.mvc;

import com.cccloud.blogs.config.security.IUser;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 作者：CC
 * 时间：2020/8/20 15:40
 * 描述：参数注入
 */

public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType() == IUser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndView, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        if (webRequest instanceof ServletWebRequest) {
            String header = ((ServletWebRequest) webRequest).getRequest().getHeader("Content-Type");
            System.out.println(header);
        } 
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}