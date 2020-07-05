package com.cccloud.blogs.task.support;

import com.cccloud.blogs.task.model.TaskContext;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 15:20
 * 描述：管理线程的上下文信息给用户做相关操作
 */
public class DefaultExecutorPostProcessor extends AbstractExecutorPostProcessor {


    @Override
    public Runnable addTask(TaskContext taskContext) {
        return taskContext;
    }

    @Override
    public void beforeTaskExecute(TaskContext taskContext) {

    }

    @Override
    public void afterTaskExecutor(TaskContext taskContext, Throwable throwable) {

    }
}
