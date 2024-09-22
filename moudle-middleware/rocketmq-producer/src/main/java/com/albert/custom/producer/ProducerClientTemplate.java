package com.albert.custom.producer;

/**
 * 消息生产者
 *
 * @author yangjunwei
 * @date 2024/9/12
 */
public interface ProducerClientTemplate {

    /**
     * 发送消息
     *
     * @param topic
     * @param msg
     * @return
     */
    String sendMessage(String topic, String msg);

    /**
     * 发送延时消息，异常重试
     *
     * @param topic            主题
     * @param msg              消息内容
     * @param startDeliverTime 延时
     */
    String sendDelayMessage(String topic, String msg, Long startDeliverTime);


}