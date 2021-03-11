package com.albert.spring.factorybean;

import com.albert.spring.util.SpringBeanUtil;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Albert
 * @date 2021/3/10 下午11:25
 */
@RestController
public class TestController {

    @GetMapping("/import/test")
    public String testImport() {
        //尝试从容器中获取未主动注入的类
        FactoryBeanServiceImpl bean = SpringBeanUtil.getBean(FactoryBeanServiceImpl.class);
        bean.print();
        return "Hello";
    }

    @SneakyThrows
    @GetMapping("/factoryBean/test")
    public String testFactoryBean() {
        //从容器中获取factoryBeanDemo 实例，返回的是FactoryBean的是 getObject()方法返回的对象
        Object bean = SpringBeanUtil.getBean("factoryBeanDemo");
        Class<?> aClass = bean.getClass();
        System.out.println(aClass.getName());
        return "Hello";
    }

    @SneakyThrows
    @GetMapping("/factoryBean/testMine")
    public String testFactoryBeanMine() {
        //尝试从容器中获取未主动注入的类
        FactoryBeanDemo bean = SpringBeanUtil.getBean("&factoryBeanDemo");
        Class<?> aClass = bean.getClass();
        System.out.println(aClass.getName());
        return "Hello";
    }


}
