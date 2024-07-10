package com.albert.javase.collection;

import cn.hutool.core.util.StrUtil;
import com.albert.javase.collection.bean.Order;
import com.albert.utils.jackson.JsonUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Albert
 * @date 2021/3/29 上午12:42
 */
public class SetDeom {

    @Test
    public void hashSetDeom(){
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        System.out.println(JsonUtil.toString(hashSet));
    }

    @Test
    public void hashMapDeom(){
        HashMap<Integer,Integer> hashMap  = Maps.newHashMap();
        Integer one = hashMap.put(1, 2);
        System.out.println(one);
        Integer two = hashMap.put(1, 3);
        System.out.println(two);
    }

    @Test
    public void linkedHashSetDemo(){
        LinkedHashSet<String> hashSet = new LinkedHashSet<>();
        hashSet.add("1");
        hashSet.add("2");
        hashSet.add("3");
        System.out.println(JsonUtil.toString(hashSet));
    }

    @Test
    public void test(){

        //无效dataId
        Set<String> ids = new HashSet<>();

        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        order.setOrderId(1);
        order.setOrderName("123");
        orders.add(order);

        //设置有效数据
        List<Order> collect = orders.stream()
                .filter(orderDo -> !ids.contains(orderDo.getOrderId())).collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect));
    }



}
