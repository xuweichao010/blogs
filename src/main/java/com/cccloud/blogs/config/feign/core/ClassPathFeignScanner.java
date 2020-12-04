package com.cccloud.blogs.config.feign.core;

import com.cccloud.blogs.config.feign.Feign;
import feign.Logger;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Set;

/**
 * 作者：CC
 * 时间：2020/8/31 11:11
 * 描述：Feign注解扫描器
 */

@Setter
@Slf4j
public class ClassPathFeignScanner extends ClassPathBeanDefinitionScanner {

    private Class<? extends FeignFactoryBean<?>> feignFactoryBean;

    private String decoderBeanName;

    private String encoderBeanName;

    private Logger.Level level;

    private String loggerBeanName;

    public ClassPathFeignScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public ClassPathFeignScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters) {
        super(registry, useDefaultFilters);
    }

    public ClassPathFeignScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment) {
        super(registry, useDefaultFilters, environment);
    }

    public ClassPathFeignScanner(BeanDefinitionRegistry registry, boolean useDefaultFilters, Environment environment, ResourceLoader resourceLoader) {
        super(registry, useDefaultFilters, environment, resourceLoader);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent() && beanDefinition.getMetadata().hasAnnotation(Feign.class.getName());
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if (beanDefinitions.isEmpty()) {
            logger.warn("No Feign was found in" + Arrays.toString(basePackages) + " package. Please check your configuration.");
        } else {
            processBeanDefinitions(beanDefinitions);
        }
        return beanDefinitions;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            String url = feignUrl(holder);
            String interceptorBeanName = feignInterceptorBeanName(holder);
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            String beanClassName = definition.getBeanClassName();
            definition.getConstructorArgumentValues().addGenericArgumentValue(beanClassName);
            definition.setBeanClass(this.feignFactoryBean);
            definition.getPropertyValues().add("url", url);
            definition.getPropertyValues().add("interceptor", new RuntimeBeanReference(interceptorBeanName));
            definition.getPropertyValues().add("level", this.level);
            definition.getPropertyValues().add("encoder", new RuntimeBeanReference(this.encoderBeanName));
            definition.getPropertyValues().add("decoder", new RuntimeBeanReference(this.decoderBeanName));
            definition.getPropertyValues().add("level", this.level);
            definition.getPropertyValues().add("logger", new RuntimeBeanReference(this.loggerBeanName));
            definition.setLazyInit(false);
        }
    }

    private String feignInterceptorBeanName(BeanDefinitionHolder beanDefinition) {
        Feign feign = feign(beanDefinition);
        String interceptorBeanName = feign.interceptor().getSimpleName();
        if (this.getRegistry() != null && !this.getRegistry().containsBeanDefinition(interceptorBeanName)) {
            getRegistry().registerBeanDefinition(interceptorBeanName, BeanDefinitionBuilder.genericBeanDefinition(feign.interceptor()).getBeanDefinition());
        }
        return interceptorBeanName;
    }

    private String feignUrl(BeanDefinitionHolder beanDefinition) {
        Feign feign = feign(beanDefinition);
        String propertyUrl = getEnvironment().getProperty(feign.url());
        if (StringUtils.hasText(propertyUrl)) {
            return propertyUrl;
        }
        return feign.url();
    }

    public void registerFilters() {
        this.resetFilters(true);
        this.addIncludeFilter(new AnnotationTypeFilter(Feign.class));

    }

    private Feign feign(BeanDefinitionHolder beanDefinition) {
        Feign annotation = null;
        try {
            annotation = AnnotationUtils.findAnnotation(Class.forName(beanDefinition.getBeanDefinition().getBeanClassName()), Feign.class);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(beanDefinition.getBeanDefinition().getBeanClassName(), e);
        }
        if (annotation == null) throw new RuntimeException("找不到注解信息" + Feign.class.getName());
        return annotation;
    }


}
