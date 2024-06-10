package com.albert.netty.vo;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description ：消息ID生成器
 */
public class MakeMsgID {

    private static AtomicLong msgId = new AtomicLong(1);

    public static long getID(){
        return msgId.getAndIncrement();
    }
}
