package com.albert.boot.autoconfig;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 手动引入rocketmq自动配置
 * @author yangjunwei
 * @date 2024/9/14
 */
@Configuration
@ConditionalOnProperty(prefix = "custom.rocketmq", name = "enable", havingValue = "true")
@Import(RocketMQAutoConfiguration.class)
public class CustomRocketMqAutoConfiguration {


}
