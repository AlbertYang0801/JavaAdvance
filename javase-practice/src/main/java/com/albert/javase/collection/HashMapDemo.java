package com.albert.javase.collection;

import cn.hutool.json.JSONUtil;
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
        System.out.println(JSONUtil.toJsonStr(map));
    }


}
