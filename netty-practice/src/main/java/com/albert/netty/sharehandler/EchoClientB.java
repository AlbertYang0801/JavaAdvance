package com.albert.netty.sharehandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * @author yjw
 * @date 2024/6/5 23:32
 */
public class EchoClientB {

    private final int port;
    private final String host;

    public EchoClientB(int port, String host) {
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
                    .remoteAddress(new InetSocketAddress(host,port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
            //异步连接到服务器，sync()会阻塞到完成
            ChannelFuture sync = bootstrap.connect().sync();
            //f.channel().closeFuture().sync()
            sync.channel().closeFuture().sync();

        }finally {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println("client server start!");
        new EchoClientB(9999,"127.0.0.1").start();
    }



}
