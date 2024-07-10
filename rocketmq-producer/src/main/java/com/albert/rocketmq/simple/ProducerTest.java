package com.albert.rocketmq.simple;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 同步发送消息
 *
 * @author admin
 * @date 2024/6/24 16:08
 */
public class ProducerTest {

    private static String nameServer = "10.10.102.83:9876";

    /**
     * 同步发送消息
     */
    @SneakyThrows
    @Test
    public void send() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("simpleProducer");
        defaultMQProducer.setNamesrvAddr(nameServer);
        defaultMQProducer.start();

        for (int i = 0; i < 20; i++) {
            try {
                Message message = new Message("test", "tagA", "TID-161455", "Hello World".getBytes(RemotingHelper.DEFAULT_CHARSET));
                //同步发送消息，等待接收返回值
                SendResult send = defaultMQProducer.send(message);
                System.out.println(send);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 异步发送消息
     */
    @SneakyThrows
    @Test
    public void sendAsync() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("asyncProducer");
        defaultMQProducer.setNamesrvAddr(nameServer);
        defaultMQProducer.start();
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(3);

        int messageCount = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                Message message = new Message("async", "tagB", "TID-10000", "Async".getBytes(RemotingHelper.DEFAULT_CHARSET));
                final int index = i;
                //异步发送消息，配置回调函数接收返回值
                defaultMQProducer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                    }

                    @Override
                    public void onException(Throwable e) {
                        countDownLatch.countDown();
                        System.out.printf("%-10d Exception %s %n", index, e);
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countDownLatch.await(20, TimeUnit.SECONDS);
        defaultMQProducer.shutdown();
    }

    /**
     * 单向发送消息
     */
    @SneakyThrows
    @Test
    public void oneWaySend() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("onewayProducer");
        defaultMQProducer.setNamesrvAddr(nameServer);
        defaultMQProducer.start();

        int messageCount = 5;
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);
        for (int i = 0; i < messageCount; i++) {
            try {
                Message message = new Message("oneway", "tagB", "TID-oneway", "oneway".getBytes(RemotingHelper.DEFAULT_CHARSET));
                //单向发送消息，没有返回值
                defaultMQProducer.sendOneway(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(5000);
        defaultMQProducer.shutdown();
    }




}
