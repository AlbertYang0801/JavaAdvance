package com.albert.netty.server;

import com.albert.netty.kyrocodec.KryoDecoder;
import com.albert.netty.kyrocodec.KryoEncoder;
import com.albert.netty.server.async.DefaultTaskProcessor;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author yjw
 * @date 2024/6/10 17:01
 */
public class ServerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //网络监控
        socketChannel.pipeline().addLast(new MetricsHandler());
        //1.解决粘包半包
        //包最大长度、包长度字段偏移量、包长度字段的长度、长度的修正值、丢弃的长度数量、是否快速失败-true
        socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(65535,
                0,2,0,
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
        socketChannel.pipeline().addLast(new LoginAuthRespHandler());
        //心跳
        socketChannel.pipeline().addLast(new HeartBeatRespHandler());
        //业务处理
        socketChannel.pipeline().addLast(new ServerBusiHandler(new DefaultTaskProcessor()));


    }


}
