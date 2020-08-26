package com.albert.study.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 线程池的创建方式
 *
 * @author Albert
 * @date 2020/8/11 10:49
 */
@Slf4j
@Component
public class ThreadPoolCreate {

    /**
     * 创建一个缓存型的线程池
     *
     * @return
     */
    public ExecutorService getCachedThreadPool() {
        //使用默认的线程工厂
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        return cachedThreadPool;
    }

    /**
     * 创建一个缓存型的线程池，使用自定义ThreadFactory
     *
     * @param threadFactory 线程工厂
     * @return
     */
    private ExecutorService getCachedThreadPool(ThreadFactory threadFactory) {
        //使用自定义的线程工厂
        ExecutorService newThreadFactoryExecutorService =
                Executors.newCachedThreadPool(new ThreadFactoryBuilder().build());
        return newThreadFactoryExecutorService;
    }

    /**
     * 创建一个定长型的线程池
     *
     * @param nThreads 线程个数
     * @return
     */
    public ExecutorService getFixedThreadPool(int nThreads) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
        return fixedThreadPool;
    }

    /**
     * 创建一个定长型的线程池
     *
     * @param nThreads      线程个数
     * @param threadFactory 自定义线程工厂
     * @return
     */
    public ExecutorService getFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        //使用自定义的线程工厂
        ExecutorService newThreadFactoryExecutorService =
                Executors.newFixedThreadPool(nThreads, threadFactory);
        return newThreadFactoryExecutorService;
    }

    /**
     * 创建一个单线程的线程池
     *
     * @return
     */
    public ExecutorService getSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        return singleThreadExecutor;
    }

    /**
     * 创建一个单线程的线程池
     *
     * @param threadFactory 自定义线程工厂
     * @return
     */
    public ExecutorService getSingleThreadExecutor(ThreadFactory threadFactory) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(threadFactory);
        return singleThreadExecutor;
    }

    /**
     * 创建一个定时的线程池
     *
     * @param corePoolSize
     * @return
     */
    public ScheduledExecutorService getScheduledThreadPool(int corePoolSize) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(corePoolSize);
        return scheduledExecutorService;
    }

    /**
     * 阿里规约推荐的创建定时线程池的方式
     *
     * @return
     */
    public ExecutorService getScheduledThreadPoolByAliBaba() {
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder()
                        .namingPattern("albert-schedule-pool-%d")
                        .daemon(true)
                        .build());
        return scheduledExecutorService;
    }

    /**
     * 阿里规约推荐的手动创建线程池
     *
     * @param corePoolSize
     * @param maximumPoolSize
     * @return
     */
    public ExecutorService getThreadPoolByAlibaba(int corePoolSize, int maximumPoolSize) {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("albert-pool-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

    /**
     * 创建一个抢占式的线程池
     * 1.8新增的线程池类型
     * @return
     */
    public ExecutorService newWorkStealingPool() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        return executorService;
    }

    /**
     * 创建一个指定并行线程数的抢占式线程池
     * 1.8新增的线程池类型
     * @param parallelism
     * @return
     */
    public ExecutorService newWorkStealingPool(int parallelism){
        //指定最大并行线程数
        ExecutorService executorService = Executors.newWorkStealingPool(parallelism);
        return executorService;
    }


    /**
     * 调用线程池的构造方法创建线程池
     *
     * @param corePoolSize    最小线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime   线程空闲的销毁时间
     * @param unit            销毁时间的单位
     * @param workQueue       任务队列
     * @param threadFactory   线程工厂
     * @param handler         任务拒绝策略
     * @return
     */
    public ExecutorService getThreadPoolByParam(int corePoolSize,
                                                int maximumPoolSize,
                                                long keepAliveTime,
                                                TimeUnit unit,
                                                BlockingQueue<Runnable> workQueue,
                                                ThreadFactory threadFactory,
                                                RejectedExecutionHandler handler) {
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        return threadPoolExecutor;
    }


}
