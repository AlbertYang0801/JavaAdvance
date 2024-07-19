package com.albert.spring.controller;

import com.albert.spring.service.ITestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class TestControllerTest {
    @Mock
    ITestService testService;
    @InjectMocks
    TestController testController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testTest() {
        when(testController.test()).thenReturn("testResponse");
        String result = testController.test();
        Assertions.assertEquals("testResponse", result);
    }

}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme