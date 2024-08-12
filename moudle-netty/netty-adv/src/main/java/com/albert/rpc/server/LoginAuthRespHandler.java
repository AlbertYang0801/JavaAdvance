package com.albert.rpc.server;

import com.albert.rpc.vo.MessageType;
import com.albert.rpc.vo.MsgHeader;
import com.albert.rpc.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 登录认证
 * 登录检查，这个处理器在客户认证通过后，其实可以移除
 *
 * @author yjw
 * @date 2024/6/10 19:36
 */
@Slf4j
public class LoginAuthRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        MyMessage loginResp = null;
        //是否是login请求
        if (MyMessage.checkMsgType(message, MessageType.LOGIN_REQ)) {
            String remote = ctx.channel().remoteAddress().toString();
            //拒绝重复登陆
            if (SecurityCenter.isDupLog(remote)) {
                //返回-1
                loginResp = buildResponse((byte) -1);
                log.warn("拒绝重复登录，应答消息 : " + loginResp);
                ctx.writeAndFlush(loginResp);
                ctx.close();
            }
            //校验白名单
            InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
            String hostAddress = address.getAddress().getHostAddress();
            if (SecurityCenter.isWhiteIP(hostAddress)) {
                //放行
                SecurityCenter.addLoginUser(remote);
                //返回0
                loginResp = buildResponse((byte) 0);
                log.info("认证通过，应答消息 : " + loginResp);
                ctx.writeAndFlush(loginResp);
            }else{
                loginResp = buildResponse((byte) -1);
                log.warn("认证失败，应答消息 : " + loginResp);
                ctx.writeAndFlush(loginResp);
                ctx.close();
            }
            //释放资源
            ReferenceCountUtil.release(msg);
        } else {
            //放行
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 删除用户缓存
        SecurityCenter.removeLoginUser(ctx.channel().remoteAddress().toString());
        ctx.close();
    }

    private MyMessage buildResponse(byte result) {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.LOGIN_RESP.value());
        message.setMsgHeader(msgHeader);
        message.setBody(result);
        return message;
    }


}
