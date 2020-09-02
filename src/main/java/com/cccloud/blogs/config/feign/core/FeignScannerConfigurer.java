package com.cccloud.blogs.config.feign.core;

import feign.Logger;
import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 作者：CC
 * 时间：2020/8/31 10:59
 * 描述：
 */
@Setter
public class FeignScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;

    private Class<? extends FeignFactoryBean<?>> factoryBean;

    private String decoderBeanName;

    private String encoderBeanName;

    private Logger.Level level;

    private String loggerBeanName;


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        ClassPathFeignScanner scanner = new ClassPathFeignScanner(registry);
        scanner.setFeignFactoryBean(factoryBean);
        scanner.setDecoderBeanName(this.decoderBeanName);
        scanner.setEncoderBeanName(this.encoderBeanName);
        scanner.setLevel(this.level);
        scanner.setLoggerBeanName(loggerBeanName);
        scanner.registerFilters();
        scanner.scan(basePackage);

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
