package com.cccloud.blogs;

import com.cccloud.blogs.config.feign.FeignScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@FeignScan("com.cccloud.blogs.open")
@SpringBootApplication
@EnableConfigurationProperties
@MapperScan("com.cccloud.blogs.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
