package com.albert.javase.log;

import org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangjunwei
 * @date 2024/7/29
 */
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("开启异步链路追踪");
        //加载自定义的MDC，支持异步传递TraceId
        TtlMDCAdapter.getInstance();
    }

}
