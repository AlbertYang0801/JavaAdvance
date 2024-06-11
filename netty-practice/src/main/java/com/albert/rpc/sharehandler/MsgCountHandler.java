package com.albert.rpc.sharehandler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 统计服务器接收和发出的报文总数
 * Sharable:Channel共享该Handler
 * ChannelDuplexHandler:能同时处理入站和出战数据。
 * @author yjw
 * @date 2024/6/6 2:09
 */
@ChannelHandler.Sharable
@Slf4j
public class MsgCountHandler extends ChannelDuplexHandler {

    /**
     * 多eventLoop共享，注意线程安全
     */
    private AtomicLong inCount = new AtomicLong(0);
    private AtomicLong outCount = new AtomicLong(0);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("收到报文总数："+inCount.incrementAndGet());
        super.channelRead(ctx, msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        log.info("发出报文总数："+outCount.incrementAndGet());
        super.flush(ctx);
    }


}
