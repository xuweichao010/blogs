package com.cccloud.blogs.task;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 14:48
 * 描述：线程执行拦截器
 */
public interface ExecutorPostProcessor {
    /**
     * 添加任务时拦截
     *
     * @param
     * @return
     */
    Runnable addTaskProcessor(Runnable r);

    /**
     * 任务执行之前执行
     *
     * @param t
     * @param r
     */
    void beforeExecuteProcessor(Thread t, Runnable r);

    /**
     * 任务执行完之后执行，异常也会执行
     *
     * @param r
     * @param t
     */
    void afterExecuteProcessor(Runnable r, Throwable t);
}
