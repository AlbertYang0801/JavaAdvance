package com.albert.javase.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/7/29
 */
@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {

    @GetMapping("/test")
    public void testLog(){
        log.info("测试接口");
    }


}
