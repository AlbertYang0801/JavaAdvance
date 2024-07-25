package com.albert.javase.collection;

import com.albert.utils.jackson.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * @author Albert
 * @date 2021/3/20 下午9:25
 */
public class ListDemo {

    @Test
    public void arrayListDemo() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        System.out.println(JsonUtil.toString(list));
        //只能在已有元素的位置，和末尾+1的位置添加元素。
        list.add(1, 2);
        System.out.println(JsonUtil.toString(list));
    }

    @Test
    public void testListIteator(){
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
            list.add("d");
        }
        System.out.println(JsonUtil.toString(list));
    }

    @Test
    public void linkedListDemo() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(2);
        linkedList.remove(2);
        System.out.println(JsonUtil.toString(linkedList));
        Integer integer = linkedList.get(2);
        System.out.println(integer);
        Iterator<Integer> iterator = linkedList.iterator();
        while (iterator.hasNext()){
            linkedList.remove(2);
        }
        System.out.println(linkedList);
    }

    @Test
    public void vectorDemo() {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        List<Integer> list = Collections.synchronizedList(vector);
        list.get(0);
        //向右位运算
        int b = 8 >> 1;
        System.out.println(b);
        int a = 1;
        list.add(a);
    }

//    @Test
//    public void testYYYY(){
//        String s = LocalDateTimeUtils.formatTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss:SSS");
//        System.out.println(s);
//    }



}
