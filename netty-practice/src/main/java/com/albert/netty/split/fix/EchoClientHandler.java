package com.albert.netty.split.fix;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yjw
 * @date 2024/6/5 23:31
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private AtomicInteger counter = new AtomicInteger(0);

    /**
     * 读取到网络数据后进行业务处理
     *
     * @param ctx
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        System.out.println("client Accept[" + byteBuf.toString(CharsetUtil.UTF_8) + "] and the counter is:" + counter.incrementAndGet());
    }

    /**
     * 连接到channel发送消息
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        new Thread(() -> {
            ByteBuf msg = null;

            for (int i = 0; i < 10; i++) {
                String request = EchoServer.REQUEST + i;
                msg = Unpooled.buffer(request.length());
                msg.writeBytes(request.getBytes());
                ctx.writeAndFlush(msg);
                System.out.println("发送数据到服务器");
            }
        }).start();
    }


}
