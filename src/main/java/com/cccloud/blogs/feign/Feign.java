package com.cccloud.blogs.feign;

import com.cccloud.blogs.feign.commons.DefaultDecoder;
import com.cccloud.blogs.feign.commons.DefaultEncoder;
import com.cccloud.blogs.feign.commons.DefaultRequestInterceptor;
import feign.RequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：CC
 * 时间：2020/8/31 10:15
 * 描述：Spring和Feign深度整合
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Feign {

    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    String url();

    /**
     * 自定义的拦截器
     * 当interceptorName 为默认值时 使用该属性
     */
    Class<? extends RequestInterceptor> interceptor() default DefaultRequestInterceptor.class;


    Class<? extends Decoder> decoder() default DefaultDecoder.class;

    Class<? extends Encoder> encoder() default DefaultEncoder.class;
}
