package com.albert.eightfeatures.stream;

import com.albert.eightfeatures.TestApplication;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Albert
 * @date 2020/7/17 11:08
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class StreamTest {

    /**
     * 测试stream的joining()方法
     * list根据指定字符串连接
     */
    @Test
    public void testJoining() {
        List<String> list = Lists.newArrayList();
        list.add("1000010007");
        list.add("1000010007");
        list.add("1000010007");
        String collect = list.stream().collect(Collectors.joining(":"));
        log.info("collect result : {}", collect);
    }

    /**
     * flatMap => 将一对多元素合并。
     */
    @Test
    public void testFlatMap() {
        List<Integer> one = Lists.newArrayList();
        one.add(1);
        one.add(2);
        one.add(3);

        Order orderOne=new Order(one);
        List<Integer> two = Lists.newArrayList();
        two.add(4);
        two.add(5);
        two.add(6);
        Order orderTwo=new Order(two);


        List<Order> orderList = Arrays.asList(orderOne,orderTwo);
        List<Integer> collect = orderList.stream().flatMap(order -> order.getItemNameList().stream()).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }


}

@Data
@AllArgsConstructor
class Order{
    private List<Integer> itemNameList;

}