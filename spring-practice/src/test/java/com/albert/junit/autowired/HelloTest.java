package com.albert.junit.autowired;

import com.albert.junit.TestApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApplication.class)
//@RunWith(SpringRunner.class)
class HelloTest {

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