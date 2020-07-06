package com.cccloud.blogs.task;

import com.cccloud.blogs.task.support.MonitorExecutorPostProcessor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 15:03
 * 描述：
 */
public class TaskTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong taskCount = new AtomicLong();
        TaskExecutorService taskExecutorService = new TaskExecutorService(3, 3, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(20), new TaskExecutorService.CallerOldRunsPolicy());
        taskExecutorService.addExecutorPostProcessor(new MonitorExecutorPostProcessor(TimeUnit.SECONDS, 10, true));
        for (int j = 0; j < 3; j++) {
            new Thread(() -> {
                for (int i = 0; i < 60; i++) {
                    taskExecutorService.submit(() -> {
                        System.out.println(Thread.currentThread().getName() + ": " + taskCount.incrementAndGet());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }, "任务:" + j).start();
        }
        Thread.sleep(1000_000);
        TimeUnit seconds = TimeUnit.MINUTES;
        System.out.println(seconds.toMillis(10));

    }
}
