package com.albert.cache.controller;

import com.albert.cache.lru.slru.SlruCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 * @date 2024/6/20 19:53
 */
@RestController
public class SlruController {

    private static SlruCache<Integer, Integer> slruCache = new SlruCache<>(3, 3);


    @GetMapping("/write")
    public void write(@RequestParam("key") Integer key) {
        slruCache.add(key, key);
    }

    @GetMapping("/get")
    public Integer get(@RequestParam("key") Integer key) {
        return slruCache.get(key);
    }

    @GetMapping("/read")
    public void read() {
        slruCache.printf();
    }


}
