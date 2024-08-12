package com.albert.rpc.server;

import com.albert.rpc.server.async.AsyncBusiProcess;
import com.albert.rpc.server.async.ITaskProcessor;
import com.albert.rpc.vo.EncryptUtils;
import com.albert.rpc.vo.MessageType;
import com.albert.rpc.vo.MsgHeader;
import com.albert.rpc.vo.MyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yjw
 * @date 2024/6/10 21:16
 */
@Slf4j
public class ServerBusiHandler extends SimpleChannelInboundHandler<MyMessage> {

    private ITaskProcessor taskProcessor;

    public ServerBusiHandler(ITaskProcessor taskProcessor) {
        super();
        this.taskProcessor = taskProcessor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyMessage msg) throws Exception {
        /*检查MD5*/
        String headMd5 = msg.getMsgHeader().getMd5();
        String calcMd5 = EncryptUtils.encryptObj(msg.getBody());
        if (!calcMd5.equals(headMd5)) {
            log.error("报文md5检查不通过：" + headMd5 + " vs " + calcMd5 + "，关闭连接");
            ctx.writeAndFlush(buildBusiResp("报文md5检查不通过，关闭连接"));
            ctx.close();
        }
        log.info("ServerBusiHandler msg: " + msg.toString());
        if (MyMessage.checkMsgType(msg, MessageType.ONE_WAY)) {
            log.info("ONE_WAY类型消息，异步处理");
            AsyncBusiProcess.submitTask(taskProcessor.execAsyncTask(msg));
        } else {
            log.info("TWO_WAY类型消息，应答");
            ctx.writeAndFlush(buildBusiResp("OK"));
        }

    }

    private MyMessage buildBusiResp(String result) {
        MyMessage message = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setType(MessageType.SERVICE_RESP.value());
        message.setMsgHeader(msgHeader);
        message.setBody(result);
        return message;
    }


}
