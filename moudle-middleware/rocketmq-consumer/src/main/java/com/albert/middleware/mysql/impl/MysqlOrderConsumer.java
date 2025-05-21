package com.albert.middleware.mysql.impl;

import com.albert.message.db.entiry.MessagesDo;
import com.albert.middleware.annotations.ConditionalGlobalMessageOnMysql;
import com.albert.middleware.mysql.AbstractConsumer;
import org.springframework.stereotype.Component;

/**
 * @author yangjunwei
 * @date 2024/9/12
 */
@Component
@ConditionalGlobalMessageOnMysql
public class MysqlOrderConsumer extends AbstractConsumer {

    public MysqlOrderConsumer() {
        super("merge-order");
    }

    @Override
    public void processMessage(MessagesDo message) {
        System.out.println("[mysql] 收到消息:"+message.getMsgId()+"  " + message.getPayload());
    }


}
