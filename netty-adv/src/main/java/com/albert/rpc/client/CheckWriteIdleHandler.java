package com.albert.rpc.client;

import io.netty.handler.timeout.IdleStateHandler;

/**
 * 写空闲检测
 * @author yjw
 * @date 2024/6/10 23:25
 */
public class CheckWriteIdleHandler extends IdleStateHandler {

    /**
     * 0 表示读空闲时间不进行检测，即不对读空闲做任何处理。
     * 8 表示写空闲时间设置为8秒。如果在这8秒内，没有数据被写入到Channel（即向远端发送数据），则会触发一个写空闲事件。
     * 0 同样表示全空闲时间不进行检测，即不关心读和写都没有活动的时间段。
     */
    public CheckWriteIdleHandler() {
        //IdleStateHandler触发的事件会被userEventTriggered捕获到
        super(0, 8, 0);
    }


}
