package com.albert.spring.dependent.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@Lazy
@Component
public class ClassA {

    ClassB classB;

    public String hello() {
        return "hello";
    }

    @Autowired
    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

}
