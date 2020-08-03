package com.albert.study.springtask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试定时任务的属性规则
 * 同步打印
 *
 * @author Albert
 * @date 2020/8/3 11:35
 */
@Slf4j
//@Component
public class SyncPrintTask {

    /**
     * 第一次任务延时1m执行
     * 每次任务结束5秒后执行下次任务(任务结束)
     */
    @Scheduled(initialDelay = 1000,fixedDelay=5000)
    public void taskPrint(){
        printMsg("第1个任务");
    }

    /**
     * 第一次任务延时2s执行
     * 每次任务开始5s后执行下次任务
     */
    @Scheduled(initialDelay = 2000,fixedRate=5000)
    public void taskSyncPrint(){
        printMsg("第2个任务");
    }

    /**
     * 每隔5s执行一次
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void taskCron(){
        printMsg("第3个任务");
    }

    /**
     * 每分钟的第5s执行
     */
    @Scheduled(cron = "5 * * * * *")
    public void taskCronMin(){
        printMsg("每分钟的第5s执行该任务");
    }

    private void printMsg(String taskName) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("{},打印该消息的时间是:{}", taskName,now);
    }


}


/**
 * initialDelay 表示第一次调用前的延迟时间，搭配使用可以达到指定任务调用顺序的效果，不能搭配cron表达式一起使用
 * fixedRate 表示任务开始的指定时间后开始下次任务
 * fixedDelay 表示任务结束的指定时间后开始下次任务
 */