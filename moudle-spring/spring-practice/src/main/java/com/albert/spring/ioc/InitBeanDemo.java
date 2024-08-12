package com.albert.spring.ioc;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yjw
 * @date 2024/3/28 23:40
 */
@Component
public class InitBeanDemo implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }

    @PostConstruct
    public void test(){
        System.out.println("post");
    }

}
