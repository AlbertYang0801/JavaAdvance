package com.albert.netty.client;

import com.albert.netty.server.SecurityCenter;
import com.albert.netty.vo.MessageType;
import com.albert.netty.vo.MsgHeader;
import com.albert.netty.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 发起登录请求
 *
 * @author yjw
 * @date 2024/6/10 19:36
 */
@Slf4j
public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {

    /**
     * 建连之后
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*发出认证请求*/
        MyMessage loginMsg = buildLoginReq();
        log.info("请求服务器认证 : " + loginMsg);
        ctx.writeAndFlush(loginMsg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        //判断是否握手成功应答
        if(MyMessage.checkMsgType(message,MessageType.LOGIN_RESP)){
            log.info("收到认证应答报文，服务器是否验证通过？");
            byte loginResult = (byte) message.getBody();
            if(loginResult!=(byte)0){
                /*握手失败，关闭连接*/
                log.warn("未通过认证，关闭连接: " + message);
                ctx.close();
            }else{
                log.info("通过认证，移除本处理器，进入业务通信 : " + message);
                ctx.pipeline().remove(this);
                ReferenceCountUtil.release(msg);
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 删除用户缓存
        SecurityCenter.removeLoginUser(ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    private MyMessage buildLoginReq() {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.LOGIN_REQ.value());
        message.setMsgHeader(msgHeader);
        return message;
    }

}
