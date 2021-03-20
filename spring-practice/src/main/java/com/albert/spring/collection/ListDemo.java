package com.albert.spring.collection;

import com.albert.utils.jackson.JsonUtil;

import java.util.ArrayList;

/**
 * @author Albert
 * @date 2021/3/20 下午9:25
 */
public class ListDemo {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.println(JsonUtil.toString(list));
        //只能添加已有元素的位置，和末尾+1
        list.add(1,2);
        System.out.println(JsonUtil.toString(list));
    }

}
