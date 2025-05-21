package com.albert.ons.rocketmq;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.exception.ONSClientException;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNull;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 动态创建消费者
 *
 * @author Albert.Yang
 */
@Slf4j
public class RocketMQConsumer implements BeanPostProcessor {

    private final Properties properties;
    private final ConfigurableEnvironment environment;
    private final List<Consumer> consumers = new ArrayList<>();

    public RocketMQConsumer(Properties properties, ConfigurableEnvironment environment) {
        log.info("RocketMQConsumer properties:{}", properties.toString());
        if (properties.get(PropertyKeyConst.AccessKey) == null
                || properties.get(PropertyKeyConst.SecretKey) == null
                || properties.get(PropertyKeyConst.NAMESRV_ADDR) == null) {
            throw new ONSClientException("consumer properties not set properly.");
        }
        this.properties = properties;
        this.environment = environment;
    }

    @Override
    public Object postProcessBeforeInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopUtils.getTargetClass(bean);
        RocketMQMessageListener listenerAnnotation = targetClass.getAnnotation(RocketMQMessageListener.class);
        if (listenerAnnotation != null && bean instanceof AbstractMessageListener) {
            // 动态创建消费者
            AbstractMessageListener listener = (AbstractMessageListener) bean;
            createAndStartConsumer(listener, listenerAnnotation);
        }
        return bean;
    }

    private void createAndStartConsumer(AbstractMessageListener listenerBean, RocketMQMessageListener annotation) {
        try {
            // 解析配置中的占位符
            String topic = environment.resolvePlaceholders(annotation.topic());
            String tags = environment.resolvePlaceholders(annotation.tags());

            // 构建消费者配置
            Properties properties = this.properties;
            // 创建消费者实例
            Consumer consumer = ONSFactory.createConsumer(properties);
            // 订阅配置（Tag支持"tag1||tag2"格式）
            consumer.subscribe(topic, tags, listenerBean);
            // 启动消费者
            consumer.start();
            consumers.add(consumer);
            log.info("Created RocketMQ Consumer: topic={}, tags={}", topic, tags);
        } catch (Exception e) {
            throw new BeanCreationException("Failed to create RocketMQ consumer", e);
        }
    }

    @PreDestroy
    public void destroy() {
        consumers.forEach(Consumer::shutdown);
        log.info("All RocketMQ consumers shutdown");
    }

}
