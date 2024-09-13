package com.albert.middleware.producer;

import cn.hutool.core.lang.UUID;
import com.albert.middleware.ProducerCli;
import com.albert.middleware.annotations.ConditionalGlobalMessageOnMysql;
import com.albert.db.entiry.MessagesDo;
import com.albert.db.service.IMessagesService;
import com.albert.middleware.enums.MessagesStatusEnum;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnMysql
@Slf4j
public class MysqlProducerCli implements ProducerCli {

    @Autowired
    IMessagesService messagesService;

    @Override
    public String sendDelayMessage(String topic, String msg, Long startDeliverTime) {
        MessagesDo messagesDO = new MessagesDo();
        messagesDO.setTopic(topic);
        messagesDO.setPayload(msg);
        messagesDO.setStatus(MessagesStatusEnum.PENDING.name());
        //最早可消费时间
        messagesDO.setEnableConsumeTime(System.currentTimeMillis() + startDeliverTime);
        messagesDO.setMsgId(UUID.fastUUID().toString());
        //打印详情日志
        System.out.println(String.format("mysql发出消息 exit[OK],msgObj=%s", JSON.toJSONString(messagesDO)));
        messagesService.save(messagesDO);
        return messagesDO.getMsgId();
    }


}
