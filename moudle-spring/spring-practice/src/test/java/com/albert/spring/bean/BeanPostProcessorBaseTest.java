package com.albert.spring.bean;

import cn.hutool.extra.spring.SpringUtil;
import com.albert.spring.AbstractBaseTest;
import com.albert.spring.entity.bean.StudentService;
import com.albert.spring.entity.bean.StudentServiceProxy;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author yangjunwei
 * @date 2024/7/19
 */
public class BeanPostProcessorBaseTest extends AbstractBaseTest {

    /**
     * 测试增强类无继承管理
     * beanName不变，beanType改变
     */
    @Test
    public void testBeanPostProcessor() {
        StudentServiceProxy studentService = SpringUtil.getBean("studentService");
        Assert.assertEquals(studentService.getEmail(),"albert@gmail.com");
        Assert.assertEquals(studentService.getClass().getName(),"com.albert.spring.bean.StudentServiceProxy");
    }

    /**
     * 测试存在继承关系
     * beanName不变，beanType改变
     *
     */
    @Test
    public void testBeanPostProcessor2() {
        //java支持向上转型，子类实例可以当作父类实例来使用
        StudentService studentService = SpringUtil.getBean(StudentService.class);
        Assert.assertEquals(studentService.getClass().getName(),"com.albert.spring.bean.StudentServiceProxy");
    }

    /**
     * 测试存在继承关系
     * beanName不变，beanType改变
     *
     */
    @Test
    public void testBeanPostProcessor3() {
        //java支持向上转型，子类实例可以当作父类实例来使用
        StudentService studentService = SpringUtil.getBean(StudentService.class);
        //obj为子类
        Assert.assertEquals(studentService.getClass().getName(),"com.albert.spring.bean.StudentServiceProxy");
        Assert.assertEquals(studentService.toString(),"proxy");
        //子类方法
        Assert.assertEquals(studentService.test(),2);
        //obj的字段属性，其实就是proxy的字段。而不是父类的字段属性
        Assert.assertNull(studentService.email);
    }


    @Test
    public void testBeans() {
        String[] beanDefinitionNames = SpringUtil.getApplicationContext().getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }
        SpringUtil.getApplicationContext().getBeanDefinitionCount();
    }




}
