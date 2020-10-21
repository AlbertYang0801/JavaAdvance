package com.albert.springtask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试异步任务
 * @author Albert
 * @date 2020/9/23 16:10
 */
@Slf4j
//@Component
public class AsyncTask {

    /**
     * 第一次任务延时1m执行
     * 每次任务结束5秒后执行下次任务(任务结束)
     */
    @Async
    @Scheduled(initialDelay = 1000,fixedDelay=5000)
    public void taskPrint(){
        printMsg("第1个异步任务");
    }

    /**
     * 第一次任务延时1s执行
     * 每次任务开始5s后执行下次任务
     */
    @Async
    @Scheduled(initialDelay = 1000,fixedRate=5000)
    public void taskSyncPrint(){
        printMsg("第2个异步任务");
    }


    private void printMsg(String taskName) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("{},打印该消息的时间是:{}", taskName,now);
    }

}
