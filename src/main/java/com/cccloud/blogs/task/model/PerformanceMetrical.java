package com.cccloud.blogs.task.model;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 20:17
 * 描述：
 */
public class PerformanceMetrical {
    private AtomicLong min = new AtomicLong();
    private AtomicLong max = new AtomicLong();
    private AtomicLong sum = new AtomicLong();

    public void set(long time) {
        long expect;
        while ((expect = min.get()) > time || expect == 0L) {
            min.compareAndSet(expect, time);
        }
        while ((expect = max.get()) < time) {
            max.compareAndSet(expect, time);
        }
        sum.addAndGet(time);
    }

    public long getMin() {
        return min.get();
    }

    public long getMax() {
        return max.get();
    }

    public long getSum() {
        return sum.get();
    }
}
