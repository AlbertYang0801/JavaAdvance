package com.albert.javase.collection;

import com.albert.utils.jackson.JsonUtil;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

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


}
