package com.cccloud.blogs.task;

import com.cccloud.blogs.task.support.MonitorExecutorPostProcessor;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 15:03
 * 描述：
 */
public class TaskTest {
    public static void main(String[] args) throws InterruptedException {
        TaskExecutorService taskExecutorService = new TaskExecutorService(3, 3, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        taskExecutorService.addExecutorPostProcessor(new MonitorExecutorPostProcessor(TimeUnit.SECONDS, 10, true));
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int finalNum = i;
            taskExecutorService.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(1000_000);
        TimeUnit seconds = TimeUnit.MINUTES;
        System.out.println(seconds.toMillis(10));

    }
}
