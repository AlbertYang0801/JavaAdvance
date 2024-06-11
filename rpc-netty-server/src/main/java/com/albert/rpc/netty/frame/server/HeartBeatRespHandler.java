package com.albert.rpc.netty.frame.server;

import com.albert.rpc.netty.frame.vo.MessageType;
import com.albert.rpc.netty.frame.vo.MsgHeader;
import com.albert.rpc.netty.frame.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 心跳
 *
 * @author yjw
 * @date 2024/6/10 19:13
 */
@Slf4j
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        //是心跳请求
        if(MyMessage.checkMsgType(message, MessageType.HEARTBEAT_REQ)){
            //心跳应答
            MyMessage heartMessageResp = buildHeartBeatMsg();
            log.info("心跳应答:"+heartMessageResp);
            ctx.writeAndFlush(heartMessageResp);
            //释放msg对象的资源
            ReferenceCountUtil.release(msg);
        }else{
            //放行
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * 同步模式，返回heartbeat标识即可
     * 异步模式，还需要返回 reqId
     * @return
     */
    private MyMessage buildHeartBeatMsg() {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.HEARTBEAT_RESP.value());
        message.setMsgHeader(msgHeader);
        return message;
    }


    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("客户端已关闭连接");
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof ReadTimeoutException){
            log.warn("客户端长时间未通信，可能已经宕机，关闭链路");
            //删除用户缓存
            SecurityCenter.removeLoginUser(ctx.channel().remoteAddress().toString());
            ctx.close();
        }
        super.exceptionCaught(ctx, cause);
    }

    public boolean cheackHeartBeatReq(MyMessage message) {
        if (Objects.isNull(message)) {
            return false;
        }
        if (Objects.isNull(message.getMsgHeader())) {
            return false;
        }
        return message.getMsgHeader().getType() == MessageType.HEARTBEAT_REQ.value();
    }


}
