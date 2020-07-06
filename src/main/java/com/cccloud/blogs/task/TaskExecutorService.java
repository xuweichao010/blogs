package com.cccloud.blogs.task;

import com.cccloud.blogs.task.model.TaskContext;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 作者：徐卫超
 * 时间：2020/7/3 14:47
 * 描述：
 */
public class TaskExecutorService extends ThreadPoolExecutor {
    private final List<ExecutorPostProcessor> executorPostProcessorList = new CopyOnWriteArrayList<>();
    private final ReentrantLock lock = new ReentrantLock(false);

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
        lock.lock();
        try {
            super.execute(runnable);
        } finally {
            if (lock.isHeldByCurrentThread()) lock.unlock();
        }
    }
    private void doExecute(Runnable runnable){
        super.execute(runnable);
    }

    @Override
    public Future<?> submit(Runnable task) {
        if (task == null) throw new NullPointerException();
        RunnableFuture<Void> ftask = newTaskFor(task, null);
        execute(ftask);
        return ftask;
    }

    public static class CallerOldRunsPolicy implements RejectedExecutionHandler {
        /**
         * Creates a {@code CallerRunsPolicy}.
         */
        public CallerOldRunsPolicy() {
        }

        /**
         * Executes task r in the caller's thread, unless the executor
         * has been shut down, in which case the task is discarded.
         *
         * @param r                  the runnable task requested to be executed
         * @param threadPoolExecutor the executor attempting to execute this task
         */
        public void rejectedExecution(Runnable r, ThreadPoolExecutor threadPoolExecutor) {
            if (threadPoolExecutor instanceof TaskExecutorService) {
                TaskExecutorService ex = (TaskExecutorService) threadPoolExecutor;
                Runnable task;
                task = threadPoolExecutor.getQueue().poll();
                ex.doExecute(r);
                if (ex.lock.isHeldByCurrentThread()) ex.lock.unlock();
                if (task == null) return;
                ex.executorPostProcessorList.forEach(executorPostProcessor -> {
                    executorPostProcessor.beforeExecuteProcessor(Thread.currentThread(), r);
                });
                Throwable throwable = null;
                try {
                    task.run();
                } catch (Throwable t) {
                    throwable = t;
                    t.printStackTrace();
                } finally {
                    Throwable finalThrowable = throwable;
                    ex.executorPostProcessorList.forEach(executorPostProcessor -> {
                        executorPostProcessor.afterExecuteProcessor(r, finalThrowable);
                    });
                }
            } else {
                r.run();
            }
        }
    }

}
