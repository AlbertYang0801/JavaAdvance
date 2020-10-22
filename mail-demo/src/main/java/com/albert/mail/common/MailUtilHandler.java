package com.albert.mail.common;

import com.albert.mail.job.SendMailTask;
import com.albert.mail.utils.ConfUtil;
import com.albert.mail.utils.MailUtil;
import com.albert.utils.jackson.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用定时线程池发送邮件
 * @author Albert
 * @date 2020/10/19 20:45
 */
@Slf4j
@Component
public class MailUtilHandler {

    @Autowired
    ConfUtil confUtil;

    /**
     * 定时线程池
     */
    private final static ScheduledExecutorService scheduledExecutorService;

    private final static Integer CORE_POOL_SIZE = 5;


    static {
        //创建定时线程池
        scheduledExecutorService = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE,
                new BasicThreadFactory.Builder()
                        .namingPattern("cmdb-schedule-pool-%d")
                        .daemon(true)
                        .build());
    }

    /**
     * 添加任务到线程池定时执行
     *
     * @param sendMailTask 任务
     * @param time         固定的时间间隔之后执行
     */
    public static void addSendMailTask(SendMailTask sendMailTask, Long time) {
        log.info("添加任务到定时线程池，sendMailTask：{},time:{}", JsonUtil.toString(sendMailTask), time);
        scheduledExecutorService.schedule(sendMailTask, time, TimeUnit.MILLISECONDS);
    }


}
