package com.albert.spring.autowired.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/17
 */
@Component
public class BeanTest {

    public BeanTest(@Value("${server.port}") String port) {
        System.out.println("spring.port =>" + port);
    }

    /**
     * Bean工厂方法，入参没有被Spring管理，抛异常。
     * @param name
     * @return
     */
    //@Bean
    //public User user(String name) {
    //    return new User();
    //}


}
