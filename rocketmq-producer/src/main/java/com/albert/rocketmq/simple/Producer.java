package com.albert.rocketmq.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 同步发送消息
 * @author admin
 * @date 2024/6/24 16:08
 */
public class Producer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("simpleProducer");
        defaultMQProducer.setNamesrvAddr("10.10.102.83:9876");
        defaultMQProducer.start();

        for (int i = 0; i < 20; i++) {
            try {
                Message message = new Message("test","tagA","TID-161455","Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));
                //同步发送消息
                defaultMQProducer.sendOneway(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
