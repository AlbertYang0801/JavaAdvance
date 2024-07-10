package com.albert.rocketmq.tag;

import com.albert.rocketmq.contants.MqContants;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 使用SQL过滤需要在broker.conf中增加配置 enablePropertyFilter=true。 这个属性默认是false。
 * @author yangjunwei
 * @date 2024/7/4
 */
public class SqlFilterProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        producer.setNamesrvAddr(MqContants.NAMESERVER_ADDR);
        producer.start();
        String[] tags = new String[] {"TagA", "TagB", "TagC"};
        for (int i = 0; i < 15; i++) {
            Message msg = new Message("SqlFilterTest",
                    tags[i % tags.length],
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            msg.putUserProperty("a", String.valueOf(i));

            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }


}
