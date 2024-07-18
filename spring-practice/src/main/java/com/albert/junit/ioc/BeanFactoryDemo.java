package com.albert.junit.ioc;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author Albert
 * @date 2021/3/14 下午10:18
 */
public class BeanFactoryDemo {

    public static void main(String[] args) {
        //职责比较单一，只生产Bean
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //封装bean
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(Car.class);
        //可手动注入
        beanFactory.registerBeanDefinition("car",beanDefinition);

        //直接获取不到Bean
        Car car = beanFactory.getBean(Car.class);
        System.out.println(car);
    }

}
