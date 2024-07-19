package com.albert.spring.dependent;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * java类的循环依赖
 * 核心就是如果创建对象和属性注入是一体的，这种就是强依赖，无法解决。
 * 循环依赖
 *
 * @author yangjunwei
 * @date 2024/7/17
 */
public class JavaCircularDependency {


    public static void main(String[] args) {
        //这种强依赖的关系是创建不出来的
        //A a = new A(new B(new A(new B(new A()))));
    }

}


class A {
    private final B b;

    //构造方法创建对象+属性注入
    A(@Autowired B b) {
        this.b = b;
    }
}

class B {
    private final A a;

    B(@Autowired A a) {
        this.a = a;
    }


}