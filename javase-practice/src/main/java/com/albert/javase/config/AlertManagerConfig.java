package com.albert.javase.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangjunwei
 * @date 2023/2/22 3:08 下午
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mappings")
@PropertySource(value = {"classpath:mcms_arms_zh.properties"})
public class AlertManagerConfig {

    /**
     * RAMAction -> actions
     */
    private AlertManagerActionMapping actionMapping;

}
