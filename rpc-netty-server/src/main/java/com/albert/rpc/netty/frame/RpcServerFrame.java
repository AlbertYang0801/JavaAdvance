package com.albert.rpc.netty.frame;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.rpc.netty.frame.register.RegisterService;
import com.albert.rpc.netty.remote.SendSms;
import com.albert.rpc.netty.remote.impl.SendSmsImpl;
import com.albert.rpc.netty.frame.server.ServerInit;
import com.albert.rpc.netty.frame.vo.NettyConstant;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Netty组件初始化、监听端口、实际服务注册
 *
 * @author yangjunwei
 * @date 2024-06-11
 */
@Service
@Slf4j
public class RpcServerFrame implements Runnable {

    @Autowired
    private ServerInit serverInit;
    @Autowired
    private RegisterService registerService;

    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workGroup = new NioEventLoopGroup();

    private void bind() throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(serverInit)
                //等待建联的 socket 队列长度
                .option(ChannelOption.SO_BACKLOG, 1024);

        ChannelFuture channelFuture = serverBootstrap.bind(NettyConstant.SERVER_PORT).sync();
        log.info("网络服务已准备好，可以进行业务操作了....... :  {}:{} ", NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
    }

    /**
     * 类销毁关闭 netty 的 eventLoopGroup
     *
     * @throws InterruptedException
     */
    @PreDestroy
    public void stop() throws InterruptedException {
        bossGroup.shutdownGracefully().sync();
        workGroup.shutdownGracefully().sync();
    }

    @Override
    public void run() {
        try {
            bind();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void start() {
        // 改为注解扫描统一注册类名
//        registerService.regRemote(SendSms.class.getName(), SendSmsImpl.class);

        //TODO 向注册中心注册自身服务


        //开启服务
        ThreadUtil.newSingleExecutor().submit(this);
    }


}
