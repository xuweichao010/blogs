package com.cccloud.blogs.config.feign.core;

import com.cccloud.blogs.config.feign.FeignScan;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.mybatis.spring.annotation.MapperScannerRegistrar;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 作者：CC
 * 时间：2020/8/31 10:47
 * 描述：
 */
public class FeignScannerRegistrar implements ImportBeanDefinitionRegistrar {


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(FeignScan.class.getName()));
        if (mapperScanAttrs != null) {
            registerBeanDefinitions(importingClassMetadata, mapperScanAttrs, registry,
                    generateBaseBeanName(importingClassMetadata));
            System.out.println(generateBaseBeanName(importingClassMetadata));
        }
    }


    void registerBeanDefinitions(AnnotationMetadata annoMeta, AnnotationAttributes annoAttrs,
                                 BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(FeignScannerConfigurer.class);
        //包扫描信息
        List<String> basePackages = new ArrayList<>();
        basePackages.addAll(
                Arrays.stream(annoAttrs.getStringArray("value")).filter(StringUtils::hasText).collect(Collectors.toList()));
        if (basePackages.isEmpty()) {
            basePackages.add(getDefaultBasePackage(annoMeta));
        }
        builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackages));

        //factoryBean
        Class<? extends FeignFactoryBean> mapperFactoryBeanClass = annoAttrs.getClass("factoryBean");
        if (!MapperFactoryBean.class.equals(mapperFactoryBeanClass)) {
            builder.addPropertyValue("factoryBean", mapperFactoryBeanClass);
        }
        //处理解析器
        String decoderName = annoAttrs.getString("decoderName");
        if (StringUtils.isEmpty(decoderName)) {
            Class<? extends Decoder> decoderClass = annoAttrs.getClass("decoder");
            decoderName = generateBaseBeanName(decoderClass);
            registry.registerBeanDefinition(decoderName,
                    BeanDefinitionBuilder.genericBeanDefinition(decoderClass).getBeanDefinition());
        }
        builder.addPropertyValue("decoderBeanName", decoderName);
        //处理编码器
        String encoderName = annoAttrs.getString("encoderName");
        if (StringUtils.isEmpty(encoderName)) {
            Class<? extends Encoder> encoderClass = annoAttrs.getClass("encoder");
            encoderName = generateBaseBeanName(encoderClass);
            registry.registerBeanDefinition(encoderName,
                    BeanDefinitionBuilder.genericBeanDefinition(encoderClass).getBeanDefinition());
        }
        builder.addPropertyValue("encoderBeanName", encoderName);
        //处理日志级别
        Logger.Level level = annoAttrs.getEnum("level");
        builder.addPropertyValue("level", level);
        //处理日志框架
        Class<? extends Logger> loggerClass = annoAttrs.getClass("logger");
        String loggerName = generateBaseBeanName(loggerClass);
        registry.registerBeanDefinition(loggerName,
                BeanDefinitionBuilder.genericBeanDefinition(loggerClass).getBeanDefinition());
        builder.addPropertyValue("loggerBeanName", loggerName);
        //注册bean的定义信息
        registry.registerBeanDefinition(beanName, builder.getBeanDefinition());
    }

    private static String getDefaultBasePackage(AnnotationMetadata importingClassMetadata) {
        return ClassUtils.getPackageName(importingClassMetadata.getClassName());
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

    }

    private static String generateBaseBeanName(AnnotationMetadata importingClassMetadata) {
        return importingClassMetadata.getClassName() + "#" + MapperScannerRegistrar.class.getSimpleName();
    }

    public static String generateBaseBeanName(Class<?> classInfo) {
        return classInfo.getSimpleName() + "#" + MapperScannerRegistrar.class.getSimpleName();
    }
}
