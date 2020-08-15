package com.albert.study.activemq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 攀枝花项目对应的mq例子
 */
//@Component
@Slf4j
public class PanzhihuaConsumer {

    /**
     * 监听mq队列
     *
     * @param text
     */
    @JmsListener(destination = "${activemq.topoc.test}")
    public void readMsg(String text) {
        try {
            JSONObject maps = (JSONObject) JSON.parse(text);
            JSONArray userinfo = maps.getJSONArray("userInfos");
            String account = (String) ((JSONObject) userinfo.get(0)).get("account");
            String pwd = (String) ((JSONObject) userinfo.get(0)).get("md5Pwd");
            System.out.println("接收到" + account + " 的消息：" + text);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

}

