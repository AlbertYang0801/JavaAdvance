package com.albert.spring.entity.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/19
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof StudentService) {
            return new StudentServiceProxy("albert@gmail.com");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }


}
