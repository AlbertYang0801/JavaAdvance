package com.albert.study.thread.threadpool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

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
     * @return
     */
    public ExecutorService getSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        return singleThreadExecutor;
    }

    /**
     * 创建一个单线程的线程池
     * @param threadFactory 自定义线程工厂
     * @return
     */
    public ExecutorService getSingleThreadExecutor(ThreadFactory threadFactory) {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(threadFactory);
        return singleThreadExecutor;
    }



}
