package com.albert.study.test;

import com.albert.study.TestApplication;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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

    @Test
    public void testGuava1(){

        List<String> list = Lists.newArrayList();
        list.add("1000010007");
        list.add("1000010007");

        list.add("1000010007");
        String s = list.toString();
        System.out.println(s);

        Map map = Maps.newHashMap();
        map.put("code",s);
        System.out.println(map);


        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);

    }




}
