package com.albert.custom.producer.impl;

import cn.hutool.core.lang.UUID;
import com.albert.custom.annotations.ConditionalGlobalMessageOnMysql;
import com.albert.custom.producer.ProducerClientTemplate;
import com.albert.message.db.entiry.MessagesDo;
import com.albert.message.db.enums.MessagesStatusEnum;
import com.albert.message.db.service.MessagesTemplate;
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
public class MysqlProducerClient implements ProducerClientTemplate {

    @Autowired
    MessagesTemplate messagesTemplate;

    @Override
    public String sendMessage(String topic, String msg) {
        return saveMessage(topic, msg, System.currentTimeMillis());
    }

    @Override
    public String sendDelayMessage(String topic, String msg, Long startDeliverTime) {
        return saveMessage(topic, msg, System.currentTimeMillis() + startDeliverTime);
    }

    private String saveMessage(String topic, String msg, long enableConsumeTime) {
        MessagesDo messagesDO = new MessagesDo();
        messagesDO.setTopic(topic);
        messagesDO.setPayload(msg);
        messagesDO.setStatus(MessagesStatusEnum.PENDING.name());
        //最早可消费时间
        messagesDO.setEnableConsumeTime(enableConsumeTime);
        messagesDO.setMsgId(UUID.fastUUID().toString());
        log.info(String.format("mysql发出消息 exit[OK],msgObj=%s", messagesDO));
        messagesTemplate.save(messagesDO);
        return messagesDO.getMsgId();
    }


}
