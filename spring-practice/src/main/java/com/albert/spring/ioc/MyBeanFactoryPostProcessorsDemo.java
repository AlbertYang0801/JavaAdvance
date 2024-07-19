package com.albert.spring.ioc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ScannedGenericBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 自定义BeanFactory的后置处理器
 * @author Albert
 * @date 2021/3/14 下午11:15
 */
@Component
public class MyBeanFactoryPostProcessorsDemo implements BeanFactoryPostProcessor, BeanDefinitionRegistryPostProcessor {
    /**
     * BeanFactory的后置处理器
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        ScannedGenericBeanDefinition car = (ScannedGenericBeanDefinition) factory.getBeanDefinition("car");
        //修改Bean的BeanDefinition
        car.setBeanClass(Tank.class);
    }

    /**
     * BeanDefinition的后置处理器
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
        rootBeanDefinition.setBeanClass(Car.class);
        beanDefinitionRegistry.registerBeanDefinition("car2",rootBeanDefinition);
    }


}
