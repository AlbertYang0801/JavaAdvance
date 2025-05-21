package com.albert.ons.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * 阿里云账户配置
 *
 * @author linn
 */
@Data
@ConfigurationProperties(prefix = "aliyunmq")
public class AliyunMQProperties {

    /**
     * 阿里云账号AccessKeyId
     */
    private String accessKey;

    /**
     * 阿里云账号AccessKeySecret
     */
    private String secretKey;

}
