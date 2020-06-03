package com.cccloud.blogs.config.feign.commons;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 18:38
 * 描述：
 */
@Configuration
public class FeignConfig {

    //请求Header Content-Type
    public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";

    //请求Header Accept
    public static final String HTTP_HEADER_ACCEPT = "Accept";

    @Bean
    public Slf4jLogger slf4jLogger() {
        return new Slf4jLogger();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
