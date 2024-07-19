package com.albert.spring.aware;

import com.albert.spring.AbstractBaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

public class BeanAwareTest extends AbstractBaseTest {

    @Autowired
    BeanAware beanAware;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSayHello() throws Exception {
        String result = beanAware.sayHello();
        Assert.assertEquals("hello", result);
    }


}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme