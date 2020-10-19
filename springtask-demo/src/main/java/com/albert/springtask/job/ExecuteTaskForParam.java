package com.albert.springtask.job;

import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.time.LocalDateTime;

/**
 * @author Albert
 * @date 2020/10/19 10:29
 */
public class ExecuteTaskForParam implements SchedulingConfigurer {

    public static Long time;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(()-> System.out.println(time),triggerContext -> {
            System.out.println(LocalDateTime.now());return new PeriodicTrigger(time).nextExecutionTime(triggerContext);
        });
    }
}
