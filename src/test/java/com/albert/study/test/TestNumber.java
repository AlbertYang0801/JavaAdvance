package com.albert.study.test;

import com.albert.study.TestApplication;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ConcurrentMap;

/**
 * @author Albert
 * @date 2020/7/17 11:08
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestNumber {



    @Test
    public void testGuava(){

        ConcurrentMap<Object, Object> objectObjectConcurrentMap = Maps.newConcurrentMap();


    }




}
