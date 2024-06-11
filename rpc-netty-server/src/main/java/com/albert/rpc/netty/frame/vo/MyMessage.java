package com.albert.rpc.netty.frame.vo;

import lombok.Data;

import java.util.Objects;

/**
 * 消息实体类
 *
 * @author yjw
 */
@Data
public final class MyMessage {

    /**
     * 请求头
     */
    private MsgHeader msgHeader;

    /**
     * 请求体
     */
    private Object body;

    public static boolean checkMsgType(MyMessage message, MessageType messageType) {
        if (Objects.isNull(message)) {
            return false;
        }
        if (Objects.isNull(message.getMsgHeader())) {
            return false;
        }
        return message.getMsgHeader().getType() == messageType.value();
    }


}
