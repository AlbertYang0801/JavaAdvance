package com.albert.spring.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestServiceImplTest {

    TestServiceImpl testServiceImpl = new TestServiceImpl();

    @Test
    void testTest() {
        String result = testServiceImpl.test();
        Assertions.assertEquals("test", result);
    }


}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme