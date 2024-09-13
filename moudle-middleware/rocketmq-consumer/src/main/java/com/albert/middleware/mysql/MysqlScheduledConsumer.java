package com.albert.middleware.mysql;

import cn.hutool.core.thread.NamedThreadFactory;
import com.albert.middleware.annotations.ConditionalGlobalMessageOnMysql;
import com.albert.middleware.mysql.impl.MysqlOrderConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@EnableScheduling
@Component
@ConditionalGlobalMessageOnMysql
public class MysqlScheduledConsumer {


    private static int taskCount = 1;

    /**
     * 消费线程池
     */
    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(taskCount + 1, taskCount * 2, 10, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(100), new NamedThreadFactory("MYSQL-CONSUMER", false));

    @Autowired
    MysqlOrderConsumer mysqlOrderConsumer;

    @Scheduled(cron = "${message.mysql.consumer.corn}")
    public void consumer() {
        threadPoolExecutor.submit(() -> mysqlOrderConsumer.consumer());
    }


}
