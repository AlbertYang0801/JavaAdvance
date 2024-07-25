package com.albert.spring.dependent.lazy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@Component
public class ClassB {

    ClassA classA;

    @Autowired
    public void setClassA( ClassA classA) {
        this.classA = classA;
    }

    public String helloA(){
        return classA.hello();
    }


}
