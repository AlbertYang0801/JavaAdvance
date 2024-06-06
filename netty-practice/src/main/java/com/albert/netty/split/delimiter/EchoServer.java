package com.albert.netty.split.delimiter;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 基于netty的服务器
 *
 * @author yjw
 * @date 2024/6/5 22:59
 */
@Slf4j
public class EchoServer {

    private final int port;

    public static final String DELIMITER_SYMBOL = "@~";

    public EchoServer(int port) {
        this.port = port;
    }

    @SneakyThrows
    public static void main(String[] args) {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("netty server start!");
        echoServer.start();
    }

    private void start() throws InterruptedException {
        //线程组
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(eventLoopGroup)
                    //指定使用NIO的通信模式
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //设置 I/O处理类,主要用于网络I/O事件，记录日志，编码、解码消息
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {

                            //处理器的作用是根据前面定义的delimiter来分割接收到的数据流，将其切分成多个完整的消息。
                            //每个完整的消息是一个ByteBuf对象，它包含了从上一个分隔符到当前分隔符之间的所有数据。
                            //参数1024是单个消息的最大长度，如果接收到的数据超过了这个长度但仍然没有遇到分隔符，那么将会抛出一个异常。
                            ByteBuf byteBuf = Unpooled.copiedBuffer(DELIMITER_SYMBOL.getBytes());
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, byteBuf));

                            //添加自定义的eventHandler
                            socketChannel.pipeline().addLast(new EchoServceHandler());
                        }
                    });
            //异步绑定到服务器，sync()会阻塞到完成
            ChannelFuture sync = serverBootstrap.bind().sync();
            //阻塞当前线程，直到服务器的ServerChannel被关闭
            sync.channel().closeFuture().sync();
        } finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }


}
