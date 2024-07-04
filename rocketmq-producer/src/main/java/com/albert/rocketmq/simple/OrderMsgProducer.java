package com.albert.rocketmq.simple;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/4
 */
public class OrderMsgProducer {

    private static String nameServer = "10.10.102.83:9876";

    /**
     * 有序发送
     */
    @SneakyThrows
    @Test
    public void orderSend() {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr(nameServer);
            producer.start();

            for (int i = 0; i < 10; i++) {
                int orderId = i;

                for (int j = 0; j <= 5; j++) {
                    Message msg =
                            //相同tag
                            new Message("OrderTopicTest", "order_" + orderId, "KEY" + orderId,
                                    ("order_" + orderId + " step " + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                        //重写select
                        //根据orderid指定messageQueue
                        @Override
                        public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                            Integer id = (Integer) arg;
                            int index = id % mqs.size();
                            return mqs.get(index);
                        }
                    }, orderId);

                    System.out.printf("%s%n", sendResult);
                }
            }
            producer.shutdown();
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 有序发送
     */
    @SneakyThrows
    @Test
    public void orderSendTwoMsg() {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
            producer.setNamesrvAddr(nameServer);
            producer.start();
            for (int j = 0; j <= 5; j++) {
                Message msg =
                        //相同tag
                        new Message("OrderTopicTest", ("order_" + " step " + j).getBytes(RemotingHelper.DEFAULT_CHARSET));
                SendResult sendResult = producer.send(msg);
                System.out.printf("%s%n", sendResult);
            }
            producer.shutdown();
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }


}
