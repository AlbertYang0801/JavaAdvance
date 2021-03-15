package com.albert.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author Albert
 * @date 2021/3/14 下午10:22
 */
@Component
public class ApplicationContextDemo {

//    public static void main(String[] args) {
//        //为容器指定配置类的位置，通过配置类加载Bean
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
//        //通过ApplicationContext获取Bean对象，实际上还是从BeanFactory获取的
//        Car car = annotationConfigApplicationContext.getBean(Car.class);
//        System.out.println(car.toString());
//    }

//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
//        //测试BeanFactoryPostProcessors后置处理器对Bean的修改(将car对象的实例，修改为tank)
//        Tank tank = (Tank) annotationConfigApplicationContext.getBean("car");
//        System.out.println(tank.toString());
//    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MyConfig.class);
        //测试BeanDefinitionRegistryPostProcessor后置处理器增加的注册BeanDefinition
        Car car = (Car) annotationConfigApplicationContext.getBean("car2");
        System.out.println(car.toString());
    }


}
