package com.albert.netty.sharehandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author yjw
 * @date 2024/6/5 23:31
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    /**
     * 读取到网络数据后进行业务处理
     * @param ctx
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("client Accept====>" + byteBuf.toString(CharsetUtil.UTF_8));
//        ctx.close();
    }

    /**
     * 连接到channel发送消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(()->{
            while (true){
                ctx.writeAndFlush(Unpooled.copiedBuffer("Hello,Netty",CharsetUtil.UTF_8));
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
