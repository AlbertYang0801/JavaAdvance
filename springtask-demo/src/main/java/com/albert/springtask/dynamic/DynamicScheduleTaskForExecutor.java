package com.albert.springtask.dynamic;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用定时线程池进行定时执行任务
 * @author Albert
 * @date 2020/10/20 10:17
 */
@Slf4j
public class DynamicScheduleTaskForExecutor {

    @Autowired
    CommonHandler commonHandler;

    /**
     * 执行的任务
     */
    static class MyTask implements Runnable {

        private String userName;
        private String age;

        public MyTask(String userName, String age) {
            this.userName = userName;
            this.age = age;
        }

        @Override
        public void run() {
            log.info("userName:{},age:{}", userName, age);
            //调用外部静态方法
            CommonHandler.print();
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
        scheduledExecutorService.schedule(new MyTask("110","10"),5L, TimeUnit.SECONDS);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
