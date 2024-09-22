package com.albert.custom.producer.impl;

import com.albert.custom.producer.ProducerClientTemplate;
import com.albert.message.db.annotations.ConditionalGlobalMessageOnClose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnClose
@Slf4j
public class CloseProducerClient implements ProducerClientTemplate {

    @Override
    public String sendMessage(String topic, String msg) {
        return "";
    }

    @Override
    public String sendDelayMessage(String topic, String msg, Long startDeliverTime) {
        return "";
    }


}
