package com.albert.rpc.split.fix;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import lombok.SneakyThrows;

import java.net.InetSocketAddress;

/**
 * 解决粘包拆包问题-固定长度
 * @author yjw
 * @date 2024/6/5 23:32
 */
public class EchoClient {

    private final int port;
    private final String host;

    public final static String REQUEST = "Mark,zhuge,zhouyu,fox,loulan";

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
                            //按照固定长度分割数据流
                            channel.pipeline().addLast(new FixedLengthFrameDecoder(REQUEST.length() + 1));
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
