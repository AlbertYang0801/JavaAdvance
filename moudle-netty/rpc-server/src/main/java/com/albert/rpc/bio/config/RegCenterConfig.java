package com.albert.rpc.bio.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 注册中心地址
 * @author yjw
 * @date 2024/5/29 22:20
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "reg.center")
public class RegCenterConfig {
    private String host;
    private int port;
}
