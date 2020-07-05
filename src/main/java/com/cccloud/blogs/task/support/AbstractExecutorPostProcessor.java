package com.cccloud.blogs.task.support;

import com.cccloud.blogs.task.ExecutorPostProcessor;
import com.cccloud.blogs.task.model.TaskContext;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 15:20
 * 描述：管理线程的上下文信息给用户做相关操作
 */
public abstract class AbstractExecutorPostProcessor implements ExecutorPostProcessor {

    @Override
    public Runnable addTaskProcessor(Runnable r) {
        return addTask((TaskContext) r);
    }

    @Override
    public void beforeExecuteProcessor(Thread t, Runnable r) {
        this.beforeTaskExecute((TaskContext) r);
    }

    @Override
    public void afterExecuteProcessor(Runnable r, Throwable t) {
        this.afterTaskExecutor((TaskContext) r, t);
    }

    public abstract Runnable addTask(TaskContext r);

    public abstract void beforeTaskExecute(TaskContext taskContext);

    public abstract void afterTaskExecutor(TaskContext taskContext, Throwable throwable);

}
