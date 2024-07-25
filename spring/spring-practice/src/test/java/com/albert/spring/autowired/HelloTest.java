package com.albert.spring.autowired;

import com.albert.spring.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

class HelloTest extends AbstractBaseTest {

    //@Autowired
    //@Qualifier("helloA")
    //private AbstractHello abstractHello;

    //@Autowired
    //@Qualifier("helloB")
    //private AbstractHello abstractHello;

    //@Autowired
    //@Qualifier("helloC")
    //private AbstractHello abstractHello;

    @Autowired
    @Qualifier("helloD")
    private HelloD helloD;


    @Test
    void sayHello() {
        System.out.println(helloD.helloService.sayHello("xiaohong"));
    }




}