package com.albert.study.activemq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
public class PanzhihuaProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Value("${activemq.topoc.test}")
    private String topic;

    public void sendMsg(String msg) {
//        jmsMessagingTemplate.convertAndSend("testmq", msg);
//        JmsMessagingTemplate jmsMessagingTemplate = jmsMessagingTemplate(jmsTemplate(connectionFactory()));
        try {
            int number = Integer.valueOf(msg);
            for (int i = 0; i < number; i++) {
                ProducerThread myThread1 = new ProducerThread();
                myThread1.setJmsMessagingTemplate(jmsMessagingTemplate);
                String json = "{\"flag\":true,\"operate\":\"addUser\",\"userInfos\":[{\"innerCode\":\"aedc720e82c84ab59c07eff1870167af\"," +
                        "\"account\":\"" + i + "@qq.com\",\"fullName\":\"周杨\",\"userStatus\":\"1\",\"leaderFlag\":\"\",\"userRankCode\":\"\",\"userRank\":\"\",\"userTypeCode\":\"\",\"sex\":\"\",\"position\":\"办事员\",\"md5Pwd\":\"65a0ec385ca6a0c1e20d1f8270c28303\",\"office\":\"\",\"mobile\":\"15928414310\",\"telephone\":\"80589200\",\"email\":\"448405567@qq.com\",\"regionCode\":\"51000000000000000000\",\"regionName\":\"四川省\",\"userType\":\"\",\"secretType\":\"4\",\"userDeptId\":\"60401185942a4440aa29a0b2536971f8\",\"userDeptName\":\"运行维护科\",\"userOrgId\":\"510000              \",\"userOrgName\":\"四川省生态环境厅\",\"returnId\":\"c96fc1844bc64d0f93358517225fe740\"}]}";
                myThread1.setJson(json);
                myThread1.start();
            }
        } catch (Exception e) {
            jmsMessagingTemplate.convertAndSend("testmq", "{\"flag\":true,\"operate\":\"addUser\",\"userInfos\":[{\"innerCode\":\"aedc720e82c84ab59c07eff1870167af\"," +
                    "\"account\":\"448405567@qq.com\",\"fullName\":\"周杨\",\"userStatus\":\"1\",\"leaderFlag\":\"\",\"userRankCode\":\"\",\"userRank\":\"\",\"userTypeCode\":\"\",\"sex\":\"\",\"position\":\"办事员\",\"md5Pwd\":\"65a0ec385ca6a0c1e20d1f8270c28303\",\"office\":\"\",\"mobile\":\"15928414310\",\"telephone\":\"80589200\",\"email\":\"448405567@qq.com\",\"regionCode\":\"51000000000000000000\",\"regionName\":\"四川省\",\"userType\":\"\",\"secretType\":\"4\",\"userDeptId\":\"60401185942a4440aa29a0b2536971f8\",\"userDeptName\":\"运行维护科\",\"userOrgId\":\"510000              \",\"userOrgName\":\"四川省生态环境厅\",\"returnId\":\"c96fc1844bc64d0f93358517225fe740\"}]}");

        }
        System.out.println("msg发送成功");
    }


    private String getJson() {

        String str = "{\"flag\":\"true\",\n" +
                "\"deptInfos\":\n" +
                "[\n" +
                "{\"id\":\"00fd015ac59f142f0001\", \"deptCode\":\"22060000D016\", \"deptName\":\"综合技术科\", \"invalidFlag\":\"1\", \"deptShortName\":\"综合技术科\", \"regionCode\":\"220600000000\", \"deptId\":\"\",\n" +
                "\"parentDeptId\":\"\",\n" +
                "\"purpose\":\"2\", \"orgMappingType\":\"\",\n" +
                "\"sortNo\":\"5\",\n" +
                "\"deptType\":\"01\", \"returnId\":\"00fd015ac59f142f9991\"} ],\"operate\":\"addDept\"}";
        return str;

    }

}
