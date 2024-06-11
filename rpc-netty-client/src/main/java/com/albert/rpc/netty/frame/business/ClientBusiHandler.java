package com.albert.rpc.netty.frame.business;

import com.albert.rpc.netty.frame.vo.MessageType;
import com.albert.rpc.netty.frame.vo.MsgHeader;
import com.albert.rpc.netty.frame.vo.MyMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yjw
 * @date 2024/6/10 23:24
 */
@Slf4j
@Service
@ChannelHandler.Sharable
public class ClientBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    private ChannelHandlerContext ctx;

    private ConcurrentHashMap<Long, BlockingQueue<Object>> respMap = new ConcurrentHashMap<>();

    /**
     * 当初始化该 Handler 时执行该方法
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        this.ctx = ctx;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        log.info("业务应答消息：" + msg.toString());
        if (Objects.nonNull(msg.getMsgHeader()) && msg.getMsgHeader().getType() == MessageType.SERVICE_RESP.value()) {
            MsgHeader msgHeader = msg.getMsgHeader();
            long sessionId = msgHeader.getSessionID();
            //应答消息时候添加到 queue，实现请求远程服务的同步
            if (respMap.containsKey(sessionId)) {
                BlockingQueue<Object> blockingQueue = respMap.get(sessionId);
                blockingQueue.put(msg.getBody());
            }
        }
    }

    /**
     * 实现同步
     * 1.调用远程服务
     * 2.同步等待响应，利用 BlockQueue，等待 Netty 的 resp 结果写入 BlockQueue。
     * @param message
     * @return
     * @throws InterruptedException
     */
    public Object send(Object message) throws InterruptedException {
        MyMessage myMessage = new MyMessage();

        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.SERVICE_REQ.value());
        Random r = new Random();
        long sessionId = r.nextLong() + 1;
        msgHeader.setSessionID(sessionId);

        myMessage.setMsgHeader(msgHeader);
        myMessage.setBody(message);
        //利用 BlockQueue 实现同步
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(1);
        respMap.put(sessionId, blockingQueue);
        ctx.writeAndFlush(message);

        //阻塞等待响应结果，channelRead处理返回数据的时候，添加到 Queue
        return blockingQueue.take();
    }



}
