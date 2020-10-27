package com.albert.concurrentpractice.thread.threadpool;

import com.albert.concurrentpractice.TestApplication;
import com.albert.concurrentpractice.threadpool.ThreadPoolCreate;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

/**
 * 测试线程池五种类型的创建
 * 测试推荐创建线程池的方式
 *
 * @author Albert
 * @date 2020/8/12 16:38
 */
@Slf4j
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringRunner.class)
public class ThreadPoolCreateTest {

    @Autowired
    private ThreadPoolCreate threadPoolCreate;

    /**
     * 测试使用CacheThreadPool,缓存类型线程池
     * 根据任务个数扩展线程个数，同时执行，每个任务对应一个线程
     */
    @Test
    public void testCacheThreadPool() {
        ExecutorService cachedThreadPool = threadPoolCreate.getCachedThreadPool();
        log.info("测试CacheThreadPool线程池执行任务，开始执行");
        executeTask(cachedThreadPool);
        log.info("测试CacheThreadPool线程池执行任务，结束完成");
    }

    /**
     * 测试使用FixedThreadPool，定长型线程池
     * 线程池永远拥有指定个数的线程池，允许同时执行指定个数，超出的任务会在队列中等待执行。
     */
    @Test
    public void testFixedThreadPool() {
        ExecutorService fixedThreadPool = threadPoolCreate.getFixedThreadPool(5);
        log.info("测试FixedThreadPool线程池执行任务，开始执行");
        executeTask(fixedThreadPool);
        log.info("测试FixedThreadPool线程池执行任务，结束完成");
    }

    /**
     * 测试使用FixedThreadPool，定长型线程池，使用自定义线程工厂ThreadFactory
     */
    @Test
    public void testFixedThreadPoolThreadFactory() {
        //创建自定义线程工厂
        ThreadFactory threadFactory =
                new ThreadFactoryBuilder().setNameFormat("albert-pool-%d").build();
        ExecutorService fixedThreadPool = threadPoolCreate.getFixedThreadPool(5, threadFactory);
        log.info("测试FixedThreadPool线程池执行任务，开始执行");
        executeTask(fixedThreadPool);
        log.info("测试FixedThreadPool线程池执行任务，结束完成");
    }

    /**
     * 测试SingleThreadExecutor,单线程的线程池
     */
    @Test
    public void testSingleThreadExecutor() {
        ExecutorService singleThreadExecutor = threadPoolCreate.getSingleThreadExecutor();
        log.info("测试SingleThreadExecutor线程池执行任务，开始执行");
        executeTask(singleThreadExecutor);
        log.info("测试SingleThreadExecutor线程池执行任务，结束完成");
    }

    /**
     * 测试定时线程池-周期执行
     */
    @Test
    public void testScheduledThreadPoolPeriod() {
        ScheduledExecutorService scheduledThreadPool = threadPoolCreate.getScheduledThreadPool(3);
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            log.info("每1秒执行一次");
        }, 1, 1, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试定时线程池-延时启动
     */
    @Test
    public void testScheduledThreadPoolDelay() {
        ScheduledExecutorService scheduledThreadPool = threadPoolCreate.getScheduledThreadPool(3);
        scheduledThreadPool.schedule(() -> {
            log.info("在系统启动5秒后执行");
        }, 5, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试定时线程池-延时启动-测试线程执行返回返回值
     */
    @Test
    public void testScheduledThreadPoolCallable() {
        ScheduledExecutorService scheduledThreadPool = threadPoolCreate.getScheduledThreadPool(3);
        //使用Callable函数式接口，获取返回值
        ScheduledFuture<String> schedule = scheduledThreadPool.schedule(() -> {
            return "测试返回值";
        }, 5, TimeUnit.SECONDS);
        try {
            System.out.println(schedule.get());
            Thread.sleep(10000);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试jdk1.8新增的线程池WorkStealingPool
     * 抢占式线程池，能充分利用CPU资源
     * 若不指定线程数，则使用的线程数为计算机当前可使用的CPU数量
     */
    @Test
    public void testNewWorkStealingPool(){
        ExecutorService executorService = threadPoolCreate.newWorkStealingPool();
        executeTask(executorService);
    }

    /**
     * 测试jdk1.8新增的线程池WorkStealingPool
     * 抢占式线程池，能充分利用CPU资源
     * 指定线程数，则最大并行数量为指定的数量
     */
    @Test
    public void testNewWorkStealingPoolByParam(){
        //指定线程最大并行数
        ExecutorService executorService = threadPoolCreate.newWorkStealingPool(3);
        executeTask(executorService);
    }

    /**
     * 测试手动创建线程池
     * 通过手动创建线程池可避免OOM(内存泄漏)的发生
     */
    @Test
    public void testAlibabaThreadPool(){
        //指定线程最小和最大线程数(相当于FixedThreadPool)
        ExecutorService threadPoolByAlibaba = threadPoolCreate.getThreadPoolByAlibaba(5, 5);
        log.info("测试手动创建线程池");
        executeTask(threadPoolByAlibaba);
    }


    /**
     * 使用不同的线程池执行任务
     *
     * @param executorService
     */
    private void executeTask(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            final int temp = i + 1;
            executorService.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("第{}个任务执行完成", temp);
            });
        }
        //防止任务不全部执行完成主线程休眠
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
