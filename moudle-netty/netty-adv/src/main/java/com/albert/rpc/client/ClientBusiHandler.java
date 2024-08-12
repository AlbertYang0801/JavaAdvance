package com.albert.rpc.client;

import com.albert.rpc.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yjw
 * @date 2024/6/10 23:24
 */
@Slf4j
public class ClientBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        log.info("业务应答消息："+msg.toString());
    }

}
