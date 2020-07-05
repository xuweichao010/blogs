package com.cccloud.blogs.task;

import com.cccloud.blogs.task.model.TaskContext;

import java.util.List;
import java.util.concurrent.*;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 14:47
 * 描述：
 */
public class TaskExecutorService extends ThreadPoolExecutor {
    private final List<ExecutorPostProcessor> executorPostProcessorList = new CopyOnWriteArrayList<>();

    {
        executorPostProcessorList.add(new ExecutorPostProcessor() {
            @Override
            public Runnable addTaskProcessor(Runnable r) {
                return new TaskContext(r);
            }

            @Override
            public void beforeExecuteProcessor(Thread t, Runnable r) {
            }

            @Override
            public void afterExecuteProcessor(Runnable r, Throwable t) {
            }
        });
    }

    public TaskExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public TaskExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public TaskExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public TaskExecutorService(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        if (executorPostProcessorList == null) return;
        executorPostProcessorList.forEach(executorPostProcessor -> {
            try {
                executorPostProcessor.beforeExecuteProcessor(t, r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        if (executorPostProcessorList == null) return;
        for (int i = executorPostProcessorList.size() - 1; i >= 0; i--) {
            try {
                executorPostProcessorList.get(i).afterExecuteProcessor(r, t);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addExecutorPostProcessor(ExecutorPostProcessor postProcessor) {
        if (postProcessor == null) throw new NullPointerException();
        executorPostProcessorList.add(postProcessor);
    }


    public void execute(Runnable command) {
        Runnable runnable = command;
        for (ExecutorPostProcessor executorPostProcessor : executorPostProcessorList) {
            try {
                runnable = executorPostProcessor.addTaskProcessor(runnable);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        super.execute(runnable);
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<Void> ftask = newTaskFor(task, null);
        execute(ftask);
        return ftask;
    }


}
