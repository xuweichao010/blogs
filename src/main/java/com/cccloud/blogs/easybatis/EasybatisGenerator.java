package com.cccloud.blogs.easybatis;

import com.cccloud.blogs.easybatis.assistant.EasybatisMapperAnnotationBuilder;
import com.cccloud.blogs.easybatis.assistant.Reflection;
import com.cccloud.blogs.easybatis.handler.impl.EnumTypeHandler;
import com.cccloud.blogs.easybatis.interfaces.EasyMapper;
import com.cccloud.blogs.easybatis.plugin.EasybatisPlugin;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.util.Collection;

/**
 * 创建人：徐卫超
 * 创建时间：2019/4/2  11:11
 * 业务：
 * 功能：
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
public class EasybatisGenerator implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent>, EnvironmentAware {
    private static final Logger logger = LoggerFactory.getLogger(EasybatisGenerator.class);

    private ApplicationContext applicationContext;
    private Configuration configuration;
    private Environment environment;

    @Bean
    public EasybatisEnvironment easybatisEnvironment() {
        return new EasybatisEnvironment();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.configuration = applicationContext.getBean(SqlSessionFactory.class).getConfiguration();

    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.configuration = applicationContext.getBean(SqlSessionFactory.class).getConfiguration();
        Collection<Class<?>> mappers = configuration.getMapperRegistry().getMappers();
        System.out.println("---------------------------------刷新完毕事件");
        if (!mappers.isEmpty() && getApplicationId(environment).equals(contextRefreshedEvent.getApplicationContext().getId())) {
            initEasybatis(configuration, mappers);
            System.out.println("---------------------------------spring boot 刷新完毕事件");
        }
    }

    private void initEasybatis(Configuration configuration, Collection<Class<?>> mappers) {
        for (Class<?> clazz : mappers) {
            if (EasyMapper.class.isAssignableFrom(clazz)) {
                Class<?> ec = Reflection.getEntityClass(clazz);
                EasybatisMapperAnnotationBuilder parser = new EasybatisMapperAnnotationBuilder(configuration, clazz, ec);
                parser.parse();
            }
        }
        configuration.setUseActualParamName(true);
        configuration.addInterceptor(new EasybatisPlugin());
        configuration.getTypeHandlerRegistry().setDefaultEnumTypeHandler(EnumTypeHandler.class);
        logger.info("easybatis 初始化完毕");
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    private String getApplicationId(Environment environment) {
        String name = environment.getProperty("spring.application.name");
        return StringUtils.hasText(name) ? name : "application";
    }
}
