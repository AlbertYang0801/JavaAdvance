package com.albert.utils.number;

import com.albert.utils.TestApplication;
import com.albert.utils.jackson.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Albert
 * @date 2020/8/1 18:22
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class NumberTest {

    /**
     * 测试Double类型转Int类型
     */
    @Test
    public void testDoubleToInt() {
        Double num = 2.99;
        //强转，只取整数位
        int i = num.intValue();
        log.info("double to int : {}", i);
    }

    @Test
    public void test1() {
        Double a = 0.00;
        System.out.println(a.intValue());

        Double b = 0.01;
        System.out.println(b.intValue());


        System.out.println(a == 0.00D);
        System.out.println(b == 0.00D);


    }

    @Test
    public void test2() {
        System.out.println(getLossListByType("day"));

    }

    private List<Object> getLossListByType(String type) {
        switch (type) {
            case "day":
                return getLossListByHour();
            case "week":
                return getLossListByWeek();
            case "month":
                return getLossListByMonth();
        }
        return Lists.newArrayList();
    }

    /**
     * 查询month的数据
     * @return
     */
    private List<Object> getLossListByMonth(){
        ArrayList<Object> objects = Lists.newArrayList();
        objects.add("1");
        return objects;

    }

    /**
     * 查询week或month的数据
     * @return
     */
    private List<Object> getLossListByWeek(){
        ArrayList<Object> objects = Lists.newArrayList();
        objects.add("2");
        return objects;
    }

    /**
     * 查询hour的数据
     * @return
     */
    private List<Object> getLossListByHour(){
        ArrayList<Object> objects = Lists.newArrayList();
        objects.add("3");
        return objects;
    }

    @Test
    public void testTime(){

        String a = "abc";

        JsonNode activeTraceBos = JsonUtil.getJsonNode(a).get("data").get("activeTraceBos");



    }


}
