package com.albert.rpc.split.delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * 解决粘包拆包问题-分割符
 *
 * @author yjw
 * @date 2024/6/5 23:32
 */
public class EchoClient {

    private final int port;
    private final String host;

    public EchoClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public void start() throws InterruptedException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup)
                    //指定使用NIO的通信模式
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            ByteBuf byteBuf = Unpooled.copiedBuffer(EchoServer.DELIMITER_SYMBOL.getBytes());
                            //按照分割符分割数据流
                            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));
                            channel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //异步连接到服务器，sync()会阻塞到完成
            ChannelFuture sync = bootstrap.connect().sync();
            //f.channel().closeFuture().sync()
            sync.channel().closeFuture().sync();

        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("client server start!");
        new EchoClient(9999, "127.0.0.1").start();
    }


}
