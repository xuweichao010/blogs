package com.cccloud.blogs.feign.core;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * 作者：CC
 * 时间：2020/8/31 10:43
 * 描述：Feign工厂Bean 用于创建代理对象
 */
@Setter
public class FeignFactoryBean<T> implements FactoryBean<T> {

    private Class<T> feignClass;
    private String url;
    private Encoder encoder;
    private Decoder decoder;
    private Logger.Level level;
    private RequestInterceptor interceptor;
    private Logger logger;

    public FeignFactoryBean(Class<T> feignClass) {
        this.feignClass = feignClass;
    }

    @Override
    public T getObject() throws Exception {
        return buildRpc();
    }

    public T buildRpc() {
        return Feign.builder().logger(logger).logLevel(level)
                .decoder(decoder).encoder(encoder).requestInterceptor(interceptor)
                .target(feignClass, url);
    }

    @Override
    public Class<?> getObjectType() {
        return feignClass;
    }


}
