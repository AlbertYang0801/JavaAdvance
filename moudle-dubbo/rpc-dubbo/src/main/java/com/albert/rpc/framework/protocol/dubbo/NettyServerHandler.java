package com.albert.rpc.framework.protocol.dubbo;

import com.albert.rpc.framework.Invocation;
import com.albert.rpc.framework.register.LocalRegister;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //服务端直接强转
        Invocation invocation = (Invocation) msg;
        //实现类
        Class implClass = LocalRegister.get(invocation.getInterfaceName());
        if (implClass == null) {
            System.out.println("implClass null");
            return;
        }
        Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
        Object invoke = method.invoke(implClass.newInstance(), invocation.getParams());
        ctx.writeAndFlush("Netty:" + invoke);
    }


}
