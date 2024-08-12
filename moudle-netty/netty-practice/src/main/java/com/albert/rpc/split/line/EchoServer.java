package com.albert.rpc.split.line;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * 解决粘包拆包问题-换行符
 *
 * @author yjw
 * @date 2024/6/5 22:59
 */
@Slf4j
public class EchoServer {

    private final int port;

    public final static String REQUEST = "Mark,zhuge,zhouyu,fox,loulan";

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

                            //LineBasedFrameDecoder依次遍历ByteBuf的可读字节，判断看是否有 "\n" 或者 "\r\n"。如果有，就以此位置为结束位置。
                            //以换行符为结束标志的解码器
                            socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //将接收到的对象转换成字符串
                            socketChannel.pipeline().addLast(new StringDecoder());
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
