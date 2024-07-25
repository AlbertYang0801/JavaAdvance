package com.albert.rpc.framework.protocol.dubbo;

import com.albert.rpc.framework.Invocation;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;

    private Invocation invocation;

    private String result;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        result = msg.toString();
        notify();
    }


    @Override
    public synchronized Object call() throws Exception {
        //向channel写数据
        context.writeAndFlush(invocation);
        wait();
        //等待结果响应
        return result;
    }

    public void setInvocation(Invocation invocation) {
        this.invocation = invocation;
    }


}
