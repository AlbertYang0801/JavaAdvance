package com.albert.ons.config;

import com.albert.ons.rocketmq.RocketMQConsumer;
import com.albert.ons.rocketmq.RocketMQProducer;
import com.albert.ons.rocketmq.RocketMQProducerImpl;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.order.OrderProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * RocketMQ自动配置类
 *
 * @author Albert.Yang
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({AliyunMQProperties.class, RocketMQProperties.class})
@ConditionalOnProperty(name = "aliyunmq.type", havingValue = "rocket")
public class RocketMQAutoConfiguration {

    @Resource
    private AliyunMQProperties aliyunProperties;

    @Resource
    private RocketMQProperties mqProperties;

    @Resource
    ConfigurableEnvironment environment;

    /**
     * 配置信息
     *
     * @return Properties
     */
    @Bean(name = "rocketProperties")
    public Properties properties() {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.AccessKey, aliyunProperties.getAccessKey());
        properties.put(PropertyKeyConst.SecretKey, aliyunProperties.getSecretKey());
        properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, mqProperties.getTimeoutMillis());
        properties.put(PropertyKeyConst.NAMESRV_ADDR, mqProperties.getNamesrvAddr());
        properties.put(PropertyKeyConst.GROUP_ID, mqProperties.getGroupId());
        properties.put(PropertyKeyConst.MessageModel, mqProperties.getMessageModel());
        properties.put(PropertyKeyConst.ConsumeThreadNums, mqProperties.getConsumeThreadNums());
        properties.put(PropertyKeyConst.ConsumeTimeout, mqProperties.getConsumeTimeout());
        properties.put(PropertyKeyConst.ConsumeMessageBatchMaxSize, mqProperties.getConsumeMessageBatchMaxSize());
        return properties;
    }

    /**
     * 普通消息、延时消息、定时消息生成者
     *
     * @return Producer
     */
    @Bean
    @ConditionalOnMissingBean(Producer.class)
    public Producer producer() {
        Producer producer = ONSFactory.createProducer(properties());
        // 只需要启动一次
        if (!producer.isStarted()) {
            producer.start();
        }
        return producer;
    }

    /**
     * 顺序消息生产者
     *
     * @return OrderProducer
     */
    @Bean
    @ConditionalOnMissingBean(OrderProducer.class)
    public OrderProducer orderProducer() {
        OrderProducer orderProducer = ONSFactory.createOrderProducer(properties());
        // 只需要启动一次
        if (!orderProducer.isStarted()) {
            orderProducer.start();
        }
        return orderProducer;
    }

    /**
     * 自动配置RocketMQService
     *
     * @return RocketMQProducer
     */
    @Bean
    @ConditionalOnMissingBean(RocketMQProducer.class)
    public RocketMQProducer rocketMQProducer() {
        RocketMQProducer mqService = new RocketMQProducerImpl();
        if (log.isInfoEnabled()) {
            log.info("RocketMQService自动配置成功...");
        }
        return mqService;
    }

    /** 若是服务中没有消费者可以不加入 */
    @Bean
    public RocketMQConsumer rocketMQConsumer() {
        log.info("执行 clusterConsumer 初始化……");
        return new RocketMQConsumer(properties(), environment);
    }

}
