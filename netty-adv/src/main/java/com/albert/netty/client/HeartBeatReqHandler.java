package com.albert.netty.client;

import com.albert.netty.vo.MessageType;
import com.albert.netty.vo.MsgHeader;
import com.albert.netty.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yjw
 * @date 2024/6/10 23:10
 */
@Slf4j
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MyMessage message = (MyMessage) msg;
        //心跳应答
        if(MyMessage.checkMsgType(message,MessageType.HEARTBEAT_RESP)){
            log.info("收到服务器心跳应答，服务器正常");
            ReferenceCountUtil.release(msg);
        }else{
            //放行
            ctx.fireChannelRead(msg);
        }
    }

    /**
     * userEventTriggered 是 Netty 中 ChannelInboundHandler 接口的一个方法，用于处理用户自定义事件
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt == IdleStateEvent.FIRST_WRITER_IDLE_STATE_EVENT){
            MyMessage heartBeat = buildHeatBeat();
            log.info("写空闲，发出心跳报文维持连接： "+ heartBeat);
            ctx.writeAndFlush(heartBeat);
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if(cause instanceof ReadTimeoutException){
            log.warn("服务器长时间未应答，关闭链路");
        }
        super.exceptionCaught(ctx, cause);
    }

    private MyMessage buildHeatBeat() {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.HEARTBEAT_REQ.value());
        message.setMsgHeader(msgHeader);
        return message;
    }

}
