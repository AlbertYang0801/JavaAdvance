package com.albert.study.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 简单的生产者
 * @author Albert
 * @date 2020/8/14 17:49
 */
@Component
//@EnableScheduling
public class ActivemqProducer {


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${activemq.topoc.test}")
    private String topic;

    /**
     * 定时发送
     */
    @Scheduled(fixedDelay = 2000)
    public void send(){
        jmsMessagingTemplate.convertAndSend(topic, "{\"flag\":true,\"operate\":\"addUser\",\"userInfos\":[{\"innerCode\":\"aedc720e82c84ab59c07eff1870167af\"," +
                "\"account\":\"448405567@qq.com\",\"fullName\":\"周杨\",\"userStatus\":\"1\",\"leaderFlag\":\"\",\"userRankCode\":\"\",\"userRank\":\"\",\"userTypeCode\":\"\",\"sex\":\"\",\"position\":\"办事员\",\"md5Pwd\":\"65a0ec385ca6a0c1e20d1f8270c28303\",\"office\":\"\",\"mobile\":\"15928414310\",\"telephone\":\"80589200\",\"email\":\"448405567@qq.com\",\"regionCode\":\"51000000000000000000\",\"regionName\":\"四川省\",\"userType\":\"\",\"secretType\":\"4\",\"userDeptId\":\"60401185942a4440aa29a0b2536971f8\",\"userDeptName\":\"运行维护科\",\"userOrgId\":\"510000              \",\"userOrgName\":\"四川省生态环境厅\",\"returnId\":\"c96fc1844bc64d0f93358517225fe740\"}]}");
    }



}
