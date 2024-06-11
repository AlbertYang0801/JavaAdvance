package com.albert.rpc.client;

import com.albert.rpc.kyrocodec.KryoDecoder;
import com.albert.rpc.kyrocodec.KryoEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author yjw
 * @date 2024/6/10 22:55
 */
public class ClientInit extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));

        //连接写空闲检测
        socketChannel.pipeline().addLast(new CheckWriteIdleHandler());

        //1.解决粘包半包
        //包最大长度、包长度字段偏移量、包长度字段的长度、长度的修正值、丢弃的长度数量、是否快速失败-true
        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,
                0, 2, 0,
                2));
        //在发送消息之前向消息的开头添加长度字段，确定消息边界
        //处理器会将消息的原始内容前面附加一个2字节的长度字段，这两个字节用来表示后续消息体的长度
        socketChannel.pipeline().addLast(new LengthFieldPrepender(2));

        //序列化
        socketChannel.pipeline().addLast(new KryoEncoder());
        socketChannel.pipeline().addLast(new KryoDecoder());

        //处理心跳超时，超时会抛出异常ReadTimeoutException
        socketChannel.pipeline().addLast(new ReadTimeoutHandler(15));

        //登录认证
        socketChannel.pipeline().addLast(new LoginAuthReqHandler());
        //心跳
        socketChannel.pipeline().addLast(new HeartBeatReqHandler());
        //业务处理
        socketChannel.pipeline().addLast(new ClientBusiHandler());


    }
}
