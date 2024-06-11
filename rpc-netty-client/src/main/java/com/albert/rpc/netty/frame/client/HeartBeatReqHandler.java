package com.albert.rpc.netty.frame.client;

import com.albert.rpc.netty.frame.vo.MessageType;
import com.albert.rpc.netty.frame.vo.MsgHeader;
import com.albert.rpc.netty.frame.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.ScheduledFuture;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yjw
 * @date 2024/6/10 23:10
 */
@Slf4j
public class HeartBeatReqHandler extends ChannelInboundHandlerAdapter {


    private volatile ScheduledFuture<?> heartBeat;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        MyMessage message = (MyMessage) msg;
        // 握手或者说登录成功，主动发送心跳消息
        if (message.getMsgHeader() != null
                && message.getMsgHeader().getType() == MessageType.LOGIN_RESP
                .value()) {
            heartBeat = ctx.executor().scheduleAtFixedRate(
                    new HeartBeatReqHandler.HeartBeatTask(ctx), 0,
                    5000,
                    TimeUnit.MILLISECONDS);
            ReferenceCountUtil.release(msg);
            //如果是心跳应答
        } else if (message.getMsgHeader() != null
                && message.getMsgHeader().getType() == MessageType.HEARTBEAT_RESP
                .value()) {
//            LOG.info("Client receive server heart beat message : ---> ");
            ReferenceCountUtil.release(msg);
            //如果是其他报文，传播给后面的Handler
        } else
            ctx.fireChannelRead(msg);
    }

    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;
        //心跳计数，可用可不用，已经有超时处理机制
        private final AtomicInteger heartBeatCount;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
            heartBeatCount = new AtomicInteger(0);
        }

        @Override
        public void run() {
            MyMessage heatBeat = buildHeatBeat();
//            LOG.info("Client send heart beat messsage to server : ---> "
//                            + heatBeat);
            ctx.writeAndFlush(heatBeat);
        }

        private MyMessage buildHeatBeat() {
            MyMessage message = new MyMessage();
            MsgHeader myHeader = new MsgHeader();
            myHeader.setType(MessageType.HEARTBEAT_REQ.value());
            message.setMsgHeader(myHeader);
            return message;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (heartBeat != null) {
            heartBeat.cancel(true);
            heartBeat = null;
        }
        ctx.fireExceptionCaught(cause);
    }


}
