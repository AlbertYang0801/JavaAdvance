package com.albert.rpc.file.server;

import cn.hutool.core.thread.ThreadUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author yangjunwei
 * @date 2024-06-12
 */
@Slf4j
public class FileNettyServer {

    private void bind(int port) throws InterruptedException {
         EventLoopGroup bossGroup = new NioEventLoopGroup();
         EventLoopGroup workGroup = new NioEventLoopGroup();
         try {
             ServerBootstrap serverBootstrap = new ServerBootstrap();
             serverBootstrap.group(bossGroup, workGroup)
                     .channel(NioServerSocketChannel.class)
                     //等待建联的 socket 队列长度
                     .option(ChannelOption.SO_BACKLOG, 1024)
                     .childHandler(new ChannelInitializer<Channel>() {
                         @Override
                         protected void initChannel(Channel channel) throws Exception {
                             channel.pipeline().addLast(new FileUploadServerHandler());
                         }
                     });
             ChannelFuture sync = serverBootstrap.bind(port).sync();
             sync.channel().closeFuture().sync();
         }finally {
             bossGroup.shutdownGracefully().sync();
             workGroup.shutdownGracefully().sync();
         }
    }

    public static void main(String[] args) throws InterruptedException {
        int port = 8900;
        new FileNettyServer().bind(port);
    }


}
