package com.cccloud.blogs.feign;

import com.cccloud.blogs.feign.commons.DefaultDecoder;
import com.cccloud.blogs.feign.commons.DefaultEncoder;
import com.cccloud.blogs.feign.commons.Slf4jLogger;
import com.cccloud.blogs.feign.core.FeignFactoryBean;
import com.cccloud.blogs.feign.core.FeignScannerRegistrar;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 作者：CC
 * 时间：2020/8/31 10:39
 * 描述：开启Feign的自动配置
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(FeignScannerRegistrar.class)
public @interface FeignScan {

    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<? extends FeignFactoryBean> factoryBean() default FeignFactoryBean.class;

    Class<? extends Decoder> decoder() default DefaultDecoder.class;

    String decoderName() default "";

    Class<? extends Encoder> encoder() default DefaultEncoder.class;

    String encoderName() default "";

    Logger.Level level() default Logger.Level.NONE;

    Class<? extends Logger> logger() default Slf4jLogger.class;
}
