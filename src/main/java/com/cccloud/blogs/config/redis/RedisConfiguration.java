package com.cccloud.blogs.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 作者：徐卫超 cc
 * 时间：2020/9/8
 * 描述：Resdis增强配置
 */
@Configuration
public class RedisConfiguration {

//    @Bean
//    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
//                                            MessageListenerAdapter listenerAdapter) {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.addMessageListener(listenerAdapter, new PatternTopic("SYS:"));
//        container.addMessageListener(listenerAdapter, new PatternTopic("testkafka1"));//配置要订阅的订阅项
//        return container;
//    }
}
