package com.albert.rpc;

import com.albert.rpc.server.ServerInit;
import com.albert.rpc.vo.NettyConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yjw
 * @date 2024/6/10 16:53
 */
@Slf4j
public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        new NettyServer().bind();
    }

    public void bind() throws InterruptedException {
        //Reactor 多线程主从模式
        //单线程负责接收Accept事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("boss"));
        //worker多线程，一个EventLoop负责多个Channel
        EventLoopGroup workerGroup = new NioEventLoopGroup(NettyRuntime.availableProcessors() * 2, new DefaultThreadFactory("worker"));
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                //等待TCP三次握手完成的队列
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ServerInit());
        serverBootstrap.bind(NettyConstant.SERVER_PORT).sync();
        log.info("Netty server start ===> {}:{}", NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
    }



}
