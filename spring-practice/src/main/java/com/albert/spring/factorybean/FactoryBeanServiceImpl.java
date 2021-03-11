package com.albert.spring.factorybean;

/**
 * 测试FactoryBean对象
 * FactoryBeanServiceImpl并没有主动注入到容器中
 * @author Albert
 * @date 2021/3/10 下午11:16
 */
public class FactoryBeanServiceImpl {

    public void print() {
        System.out.println("bean init!");
    }


}
