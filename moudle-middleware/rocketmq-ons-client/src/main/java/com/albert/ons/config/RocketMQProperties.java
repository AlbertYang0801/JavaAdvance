package com.albert.ons.config;

import cn.hutool.core.util.StrUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * rocketMQ配置
 *
 * @author linn
 */
@Data
@ConfigurationProperties(prefix = "aliyunmq.rocketmq")
public class RocketMQProperties {

    /**
     * 消息队列实例TCP接入点
     */
    private String namesrvAddr;

    /**
     * 发送超时时间，单位毫秒
     */
    private String timeoutMillis = "3000";

    /**
     * 消息组ID
     */
    private String groupId;

    /**
     * 订阅方式：CLUSTERING-集群订阅；BROADCASTING-广播订阅
     */
    private String messageModel = "CLUSTERING";

    /**
     * 消费线程数量
     */
    private String consumeThreadNums = "64";

    /**
     * 每条消息消费的最大超时时间,超过这个时间,这条消息将会被视为消费失败,等下次重新投递再次消费,每个业务需要设置一个合理的值,单位(分钟)
     */
    private String consumeTimeout = "15";

    /**
     * 每次批量消费的最大消息数量,允许自定义范围为[1, 32],实际消费数量可能小于该值.
     */
    private String consumeMessageBatchMaxSize = "1";

    private TopicConfig topic;

    public String getTopicName() {
        return getTopic().getName();
    }

    public String getTestTag() {
        return topic.getTags().getTest();
    }

    public String getTagSuffix() {
        return topic.getTagSuffix();
    }

    @Data
    public static class TopicConfig {

        private String name;

        private boolean dev;

        private String env;

        private TagConfig tags;

        public String getTagSuffix() {
            return StrUtil.isBlank(env) ? "" : ("-" + env);
        }

        @Data
        public static class TagConfig {

            private String test;

        }

    }



}
