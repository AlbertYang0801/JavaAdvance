package com.albert.junit.autowired;

import com.albert.junit.autowired.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工厂方法注入
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
@Configuration
public class AppConfig {

    /**
     * @param helloService
     * @return
     * @Configuration扫描注入Bean的同时，会扫描该类的所有方法 将加了@Bean注解的方法也注入进来
     * methodName作为BeanName，返回值作为BeanClass
     */
    @Bean(initMethod = "init", destroyMethod = "destory")
    public HelloB helloB(@Autowired HelloService helloService) {
        return new HelloB(helloService);
    }

    @Bean
    public HelloB helloResourceB(@Autowired HelloService helloService) {
        return new HelloB(helloService, "helloResourceB");
    }

    @Bean
    public HelloB helloAutowiredB(@Autowired HelloService helloService) {
        return new HelloB(helloService, "helloAutowiredB");
    }


}
