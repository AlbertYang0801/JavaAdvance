package com.albert.springtask.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试从配置文件获取定时任务的属性规则
 * 任务同步执行
 * @author Albert
 * @date 2020/8/3 16:02
 */
@Slf4j
//@Component
public class SchedulerTaskForConf {

    /**
     * 第一次任务延时1m执行
     * 每次任务结束5秒后执行下次任务(任务结束)
     */
    @Scheduled(initialDelayString ="${sync.print.task.initialDelay}" , fixedDelayString = "${sync.print.task.fixedDelay}")
    public void taskPrint() {
        printMsg("第1个任务");
    }

    /**
     * 每分钟的第5s开始执行
     */
    @Scheduled(cron = "${sync.print.task.cron}")
    public void taskCronPrint() {
        printMsg("第2个任务，每分钟的第5s执行");
    }

    private void printMsg(String taskName) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        log.info("{},打印该消息的时间是:{}", taskName, now);
    }


}
