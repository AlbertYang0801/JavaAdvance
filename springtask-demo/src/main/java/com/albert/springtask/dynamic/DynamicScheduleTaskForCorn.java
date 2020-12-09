package com.albert.springtask.dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;

/**
 * 根据cron表达式动态执行任务
 *
 * @author Albert
 * @date 2020/10/19 10:29
 */
//@Configuration
public class DynamicScheduleTaskForCorn implements SchedulingConfigurer {

    public String cron = "0/5 * * * * *";

    /**
     * 传入cron表达式，定时执行任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {

        scheduledTaskRegistrar.addTriggerTask(() -> {
                    //定时执行的任务内容
                    System.out.println("执行任务" + LocalDateTime.now());
                    System.out.println("执行任务的corn表达式为：" + cron);
                },
                triggerContext -> {
                    //2.设置任务的执行周期(cron表达式)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }


}
