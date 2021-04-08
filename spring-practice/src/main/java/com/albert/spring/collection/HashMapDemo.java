package com.albert.spring.collection;

import com.albert.utils.jackson.JsonUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author Albert
 * @date 2021/3/20 下午9:50
 */
public class HashMapDemo {

    @Test
    public void hashMapCreate(){
        HashMap<String,String> map = new HashMap<>();
        map.put("a","a");
        map.put(null,"a");
        System.out.println(JsonUtil.toString(map));
    }


}
