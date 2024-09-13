package com.albert.middleware;

/**
 * 消息生产者
 * @author yangjunwei
 * @date 2024/9/12
 */
public interface ProducerCli {

    /**
     * 发送延时消息，异常重试
     *
     * @param topic            主题
     * @param msg              消息内容
     * @param startDeliverTime 延时
     */
    String sendDelayMessage(String topic, String msg, Long startDeliverTime);


}