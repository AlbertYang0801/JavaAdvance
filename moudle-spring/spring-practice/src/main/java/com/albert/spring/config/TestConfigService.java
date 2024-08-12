package com.albert.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/8/6
 */
@Component
public class TestConfigService {

    @Value("${test.name}")
    private String name;

    @Value("${test.appkey}")
    private String appKey;

    //@Value("${test.appName}")
    private String appName;

    public String getName() {
        return name;
    }

    public String getAppkKey() {
        return appKey;
    }

    public String getAppName() {
        return appName;
    }

}
