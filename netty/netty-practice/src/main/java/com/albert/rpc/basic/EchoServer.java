package com.albert.rpc.basic;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
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

    public EchoServer(int port) {
        this.port = port;
    }

    @SneakyThrows
    public static void main(String[] args) {
        int port = 9999;
        EchoServer echoServer = new EchoServer(port);
        System.out.println("netty server start!");;
        echoServer.start();
    }

    private void start() throws InterruptedException {
        //线程组
        EventLoopGroup eventLoopGroup = new EpollEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(eventLoopGroup)
                    //指定使用NIO的通信模式
                    .channel(EpollServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    //设置 I/O处理类,主要用于网络I/O事件，记录日志，编码、解码消息
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //添加对应的eventHandler
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
