package com.albert.javase.agent.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/7/29
 */
@RestController
@RequestMapping("/agent")
public class AgentControllerTest {

    @GetMapping("/test")
    public void test() {
        System.out.println(new AgentUtilTest().getTime());
    }


}
