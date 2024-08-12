package com.albert.rpc.sharehandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 服务端 ChannelEvent 处理器
 * @author yjw
 * @date 2024/6/5 23:01
 */
@ChannelHandler.Sharable
public class EchoServceHandler extends ChannelInboundHandlerAdapter {

    /**
     * 从Channel读取消息
     * @param ctx
     * @param msg
     * @throws Exception
     */

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server accept: "+in.toString(CharsetUtil.UTF_8));
        //将消息写入Channel
        ctx.writeAndFlush(in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
