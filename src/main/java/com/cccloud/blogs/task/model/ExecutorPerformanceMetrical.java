package com.cccloud.blogs.task.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 20:40
 * 描述：线程池性能对象
 */
public class ExecutorPerformanceMetrical {
    private AtomicInteger count = new AtomicInteger();
    private long initTime = System.nanoTime();
    private PerformanceMetrical waiting = new PerformanceMetrical();
    private PerformanceMetrical running = new PerformanceMetrical();

    public ExecutorPerformanceMetrical setWaiting(long waitingTime) {
        waiting.set(waitingTime);
        return this;
    }

    public ExecutorPerformanceMetrical setRunning(long runningTime) {
        running.set(runningTime);
        return this;
    }

    public PerformanceMetrical getWaiting() {
        return waiting;
    }

    public PerformanceMetrical getRunning() {
        return running;
    }

    public ExecutorPerformanceMetrical countIncrement() {
        count.incrementAndGet();
        return this;
    }

    public int getCount() {
        return count.get();
    }

    public long waitingAvg() {
        if(count.get() == 0) return 0;
        return waiting.getSum() / count.get();
    }

    public long runningAvg() {
        if(count.get() == 0) return 0;
        return running.getSum() / count.get();
    }

    /**
     * 每秒可以处理的任务数量
     *
     * @return
     */
    public double calculatePerformance() {
        if (count.get() == 0) return 0D;
        return (System.nanoTime() - initTime) / 1000D / 1000D / count.get();
    }
}
