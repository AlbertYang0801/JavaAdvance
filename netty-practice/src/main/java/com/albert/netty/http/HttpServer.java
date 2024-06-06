package com.albert.netty.http;

import cn.hutool.socket.nio.NioServer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * http服务器
 *
 * @author yangjunwei
 * @date 2024-06-06
 */
public class HttpServer {

    //设置服务端端口
    public static final int PORT = 6789;
    // 通过nio方式来接收连接和处理连接
    private static EventLoopGroup group = new NioEventLoopGroup();
    private static ServerBootstrap serverBootstrap = new ServerBootstrap();
    //是否开启SSL模式
    public static final boolean SSL = true;

    public static void main(String[] args) throws InterruptedException {
        //TODO 兼容 ssl


        try {
            serverBootstrap.group(group)
                    .channel(NioServerSocketChannel.class);

            ChannelFuture sync = serverBootstrap.bind(PORT).sync();
            System.out.println("netty server start!");
            sync.channel().closeFuture().sync();
        } finally {
            //关闭 eventLoopGroup，释放所有线程资源
            group.shutdownGracefully();
        }
    }


}
