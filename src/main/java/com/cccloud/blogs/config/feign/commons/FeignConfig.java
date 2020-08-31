package com.cccloud.blogs.config.feign.commons;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 18:38
 * 描述：
 */
// @Configuration
public class FeignConfig {

    //请求Header Content-Type
    public static final String HTTP_HEADER_CONTENT_TYPE = "Content-Type";

    //请求Header Accept
    public static final String HTTP_HEADER_ACCEPT = "Accept";

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Bean
    public Slf4jLogger slf4jLogger() {
        return new Slf4jLogger();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    public <T> T buildRpc(Class<T> clazz, String url, RequestInterceptor interceptor) {
        return Feign.builder().logger(slf4jLogger()).logLevel(feignLoggerLevel())
                .decoder(feignDecoder()).encoder(feignEncoder()).requestInterceptor(interceptor)
                .target(clazz, url);
    }

    public <T> T buildRpc(Class<T> clazz, String url) {
        return Feign.builder().logger(slf4jLogger()).logLevel(feignLoggerLevel())
                .decoder(feignDecoder()).encoder(feignEncoder())
                .target(clazz, url);
    }

    @Bean
    public DefaultDecoder feignDecoder() {
        return new DefaultDecoder(messageConverters);
    }

    @Bean
    public DefaultEncoder feignEncoder() {
        return new DefaultEncoder(messageConverters);
    }

}
