package com.albert.rpc.frame.netty;

import com.albert.rpc.frame.register.RegisterService;
import com.albert.rpc.frame.vo.MessageType;
import com.albert.rpc.frame.vo.MsgHeader;
import com.albert.rpc.frame.vo.MyMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author yjw
 * @date 2024/6/10 21:16
 */
@Slf4j
@Service
@ChannelHandler.Sharable //实现共享
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    @Autowired
    RegisterService registerService;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        //基于反射处理 rpc 调用的逻辑
        log.info("netty server receiver msg:{}", msg);

        //反射调用对应接口
        HashMap<String, Object> bodyMap = (HashMap<String, Object>) msg.getBody();

        //className、methodName、paramType、param
        String className = bodyMap.get("className").toString();
        String methodName = bodyMap.get("methodName").toString();
        Class<?>[] parmTypes = (Class<?>[]) bodyMap.get("paramType");
        Object[] args = (Object[]) bodyMap.get("param");

        //获取 class
        Class localService = registerService.getLocalService(className);
        if (localService == null) {
            throw new ClassNotFoundException(localService + " Not Found");
        }
        Method method = localService.getMethod(methodName, parmTypes);
        //使用当前实例仅需反射调用
        Object result = method.invoke(localService.newInstance(), args);

        MyMessage respMessage = buildRespMsg(msg, result);
        ctx.writeAndFlush(respMessage);
    }

    private MyMessage buildRespMsg(MyMessage reqMessage, Object result) {
        MsgHeader reqHeader = reqMessage.getMsgHeader();

        MyMessage respMsg = new MyMessage();
        MsgHeader respHeader = new MsgHeader();
        respHeader.setSessionID(reqHeader.getSessionID());
        respHeader.setType(MessageType.SERVICE_RESP.value());
        respMsg.setMsgHeader(respHeader);
        respMsg.setBody(result);
        return respMsg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().remoteAddress() + " 主动断开了连接!");
    }


}
