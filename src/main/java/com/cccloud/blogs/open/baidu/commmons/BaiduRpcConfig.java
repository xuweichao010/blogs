package com.cccloud.blogs.open.baidu.commmons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：徐卫超
 * 时间：2020/6/3 17:27
 * 描述：百度RPC远程调用配置
 */
@Configuration
public class BaiduRpcConfig {

    @Bean
    @ConfigurationProperties(prefix = "rpc.baidu.map")
    public BaiduMapProperties baiduMapProperties() {
        return new BaiduMapProperties();
    }

}
