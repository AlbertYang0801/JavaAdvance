package com.albert.javase.agent;

import com.albert.javase.agent.util.AgentUtilTest;
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
        try {
            int i = 10/0;
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(new AgentUtilTest().getTime());
    }


}
