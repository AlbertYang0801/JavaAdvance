package com.albert.junit.autowired.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

/**
 * @author yangjunwei
 * @date 2024/7/17
 */
//@Configuration
public class HuaweiConfig {

    Huawei huawei;

    public HuaweiConfig(@Autowired Huawei huawei) {
        this.huawei = huawei;
    }

    @Bean
    Car car() {
        return new Car();
    }

}
