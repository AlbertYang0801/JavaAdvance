package com.albert.springtask.dynamic;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Albert
 * @date 2020/10/20 10:17
 */
public class DynamicScheduleTaskForExecutor {

    /**
     * 执行的任务
     */
    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("任务执行了");
        }
    }

    public static void main(String[] args) {
        //根据阿里规约推荐创建定时线程池
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder()
                        .namingPattern("albert-schedule-pool-%d")
                        .daemon(true)
                        .build());
        scheduledExecutorService.schedule(new MyTask(),1000L, TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
