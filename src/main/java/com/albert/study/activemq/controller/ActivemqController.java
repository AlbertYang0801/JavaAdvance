package com.albert.study.activemq.controller;

import com.albert.study.activemq.producer.PanzhihuaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试Activemq发送消息
 * @author Albert
 * @date 2020/7/27 20:33
 */
@RestController
public class ActivemqController {

//    @Autowired
//    private PanzhihuaProducer panzhihuaProducer;
//
//    @GetMapping("/activemq/sendmsg")
//    public void sendMsgToKafka() {
//        panzhihuaProducer.sendMsg("3");
//    }


}
