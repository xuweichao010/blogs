package com.cccloud.blogs.task.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 15:35
 * 描述：
 */
public class TaskContext implements Runnable {

    private Runnable runnable;

    public TaskContext(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * 存储华宁变量
     */
    private Map<String, Object> param;

    public TaskContext setParam(String key, Object value) {
        if (param == null) param = new HashMap<>();
        param.put(key, value);
        return this;
    }

    @SuppressWarnings("all")
    public <T> T get(String key) {
        Object value = param.get(key);
        if (value == null) return null;
        return (T) value;
    }



    @Override
    public void run() {
        runnable.run();
    }
}
