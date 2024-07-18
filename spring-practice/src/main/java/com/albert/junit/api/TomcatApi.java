package com.albert.junit.api;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2023/8/31
 */
@RequestMapping("/tomcat")
@RestController
public class TomcatApi {

    @SneakyThrows
    @GetMapping("/test")
    public void test() {
        System.out.println("请求到了:" + Thread.currentThread().toString());
        Thread.sleep(100000);
    }


}
