package com.albert.middleware.producer;

import com.albert.middleware.ProducerCli;
import com.albert.middleware.annotations.ConditionalGlobalMessageOnClose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnClose
@Slf4j
public class CloseProducerCli implements ProducerCli {

    @Override
    public String sendDelayMessage(String topic, String msg, Long startDeliverTime) {
        return "";
    }


}
