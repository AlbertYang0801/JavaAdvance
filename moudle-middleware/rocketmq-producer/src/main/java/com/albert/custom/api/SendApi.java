package com.albert.custom.api;

import com.albert.custom.producer.ProducerClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/9/20
 */
@RestController
@RequestMapping("/send")
public class SendApi {

    @Autowired
    ProducerClientTemplate producerClientTemplate;

    @GetMapping("/test")
    public String snedTest(){
        return producerClientTemplate.sendMessage("test", "hello");
    }

}
