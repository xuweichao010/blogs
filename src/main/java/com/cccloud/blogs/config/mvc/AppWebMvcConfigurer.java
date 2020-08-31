package com.cccloud.blogs.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 作者：CC
 * 时间：2020/8/20 15:46
 * 描述：
 */
@Configuration
public class AppWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * Controller 方法参数注入
     *
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new CurrentUserArgumentResolver());
    }
}
