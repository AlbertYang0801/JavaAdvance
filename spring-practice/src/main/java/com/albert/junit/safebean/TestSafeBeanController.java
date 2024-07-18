package com.albert.junit.safebean;

import com.albert.junit.util.SpringBeanUtil;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Albert
 * @date 2021/3/12 上午12:08
 */
@Controller
public class TestSafeBeanController {

    /**
     * 测试默认的Bean在有可变变量的情况下存在线程安全问题
     */
    @GetMapping("/safeBean/default")
    public void testThreadSafeBean() {
        new Thread(() -> {
            //从ApplicationContext容器中获取
            DefaultSingleBean defaultSingleBean = SpringBeanUtil.getBean(DefaultSingleBean.class);
            defaultSingleBean.msg = "线程A信息";
            System.out.println(defaultSingleBean.msg);
        }).start();
        new Thread(() -> {
            //从ApplicationContext容器中获取
            DefaultSingleBean defaultSingleBean = SpringBeanUtil.getBean(DefaultSingleBean.class);
            System.out.println(defaultSingleBean.msg);
        }).start();
    }

    /**
     * 第一种方法：测试修改Bean的作用域为prototype 来保证线程安全
     */
    @GetMapping("/safeBean/beanScope")
    public void testThreadSafeBeanScope() {
        new Thread(() -> {
            //从ApplicationContext容器中获取
            BeanScopeSafeBean beanScopeSafeBean = SpringBeanUtil.getBean(BeanScopeSafeBean.class);
            beanScopeSafeBean.msg = "线程A信息";
            System.out.println(beanScopeSafeBean.msg);
        }).start();
        new Thread(() -> {
            //从ApplicationContext容器中获取
            BeanScopeSafeBean beanScopeSafeBean = SpringBeanUtil.getBean(BeanScopeSafeBean.class);
            System.out.println(beanScopeSafeBean.msg);
        }).start();
    }

    /**
     * 第二种方式：测试使用ThreadLocal解决线程安全的问题
     */
    @SneakyThrows
    @GetMapping("/safeBean/threadlocal")
    public void testThreadSafeBeanThreadLocal() {
        new Thread(() -> {
            //从ApplicationContext容器中获取
            ThreadLocalSafeBean threadLocalSafeBean = SpringBeanUtil.getBean(ThreadLocalSafeBean.class);
            threadLocalSafeBean.threadLocalMsg.set("线程A信息");
            System.out.println(threadLocalSafeBean.threadLocalMsg.get());
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            //从ApplicationContext容器中获取
            ThreadLocalSafeBean threadLocalSafeBean = SpringBeanUtil.getBean(ThreadLocalSafeBean.class);
            System.out.println(threadLocalSafeBean.threadLocalMsg.get());
        }).start();
    }




}
