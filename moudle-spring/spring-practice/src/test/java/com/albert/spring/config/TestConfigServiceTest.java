package com.albert.spring.config;

import com.albert.spring.AbstractBaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestConfigServiceTest extends AbstractBaseTest {

    @Autowired
    TestConfigService testConfigService;

    @Test
    public void testName() {
        System.out.println(testConfigService.getName());
        System.out.println(testConfigService.getAppkKey());
        //System.out.println(testConfigService.getAppName());
    }


}

