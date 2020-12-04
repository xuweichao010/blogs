package com.cccloud.blogs.config.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * 作者：徐卫超 cc
 * 时间：2020/9/8
 * 描述：处理系统广播消息
 */
@Component
public class RedisSubscriber extends MessageListenerAdapter {

    @Override
    public void onMessage(Message message, byte[] pattern) {
    }
}
