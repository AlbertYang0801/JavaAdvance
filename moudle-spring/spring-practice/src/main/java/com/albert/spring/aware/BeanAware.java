package com.albert.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/19
 */
@Component
public class BeanAware implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String sayHello(){
        HelloAwareService bean = applicationContext.getBean(HelloAwareService.class);
        return bean.sayHello();
    }


}
