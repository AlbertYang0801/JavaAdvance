package com.albert.custom.config;

import com.albert.custom.annotations.ConditionalGlobalMessageOnRocketMq;
import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author yangjunwei
 * @date 2024/9/19
 */
@Configuration
@ConditionalGlobalMessageOnRocketMq
@Import(RocketMQAutoConfiguration.class)
public class CustomRocketmqAutoConfiguration {



}
