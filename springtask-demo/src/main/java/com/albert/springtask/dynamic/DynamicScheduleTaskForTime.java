package com.albert.springtask.dynamic;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.LocalDateTime;

/**
 * 根据时间间隔动态执行任务
 *
 * @author Albert
 * @date 2020/10/19 10:29
 */
//@Configuration
public class DynamicScheduleTaskForTime implements SchedulingConfigurer {

    /**
     * 间隔执行时间（ms）
     */
    public Long time = 10000L;

    /**
     * 传入间隔时间戳，定时执行任务
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
                    //1.定时执行的任务内容
                    System.out.println("执行任务" + LocalDateTime.now());
                    System.out.println("定时执行任务的时间间隔为:" + time);
                },
                triggerContext -> {
                    //2.设置任务的执行周期
                    return new PeriodicTrigger(time).nextExecutionTime(triggerContext);
                });
    }


}
