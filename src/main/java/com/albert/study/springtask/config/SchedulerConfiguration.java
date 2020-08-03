package com.albert.study.springtask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 定时任务SpringTask配置线程池
 * 实现任务异步调用
 *
 * @author Albert
 * @date 2020/8/3 10:47
 */
@Configuration
@EnableScheduling
public class SchedulerConfiguration implements SchedulingConfigurer {

    /**
     * 定时任务线程池大小
     */
    private static final int POOL_SIZE = 10;

    /**
     * 定时任务使用线程前缀
     */
    private static final String THREAD_NAME_PREFI = "albert-study-";

    /**
     * 创建定时任务线程池
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //初始化线程池
        threadPoolTaskScheduler.initialize();
        //设置线程池容量
        threadPoolTaskScheduler.setPoolSize(POOL_SIZE);
        //设置线程名称前缀
        threadPoolTaskScheduler.setThreadNamePrefix(THREAD_NAME_PREFI);
        //设置线程池关闭时等待所有任务关闭
        threadPoolTaskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        return threadPoolTaskScheduler;
    }

    /**
     * 设置线程池
     *
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //指定定时任务的线程池
        scheduledTaskRegistrar.setTaskScheduler(taskScheduler());
    }


}


/**
 * @EnableScheduling 注解开启定时任务配置
 * 可以加在启动类或配置类
 * 推荐加在配置类
 */