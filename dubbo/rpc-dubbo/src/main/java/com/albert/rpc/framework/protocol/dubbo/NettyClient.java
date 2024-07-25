package com.albert.rpc.framework.protocol.dubbo;

import com.albert.rpc.framework.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class NettyClient<T> {

    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private NettyClientHandler client = null;

    /**
     * 开启netty服务
     *
     * @param hostName
     * @param port
     */
    public void start(String hostName, Integer port) {
        client = new NettyClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast("decoder", new ObjectDecoder(ClassResolvers
                                .weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                        pipeline.addLast("encoder", new ObjectEncoder());
                        //自定义业务处理器
                        pipeline.addLast("handler", client);
                    }
                });
        try {
            bootstrap.connect(hostName, port).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向netty发数据
     * @param hostName
     * @param port
     * @param invocation
     * @return
     */
    public String send(String hostName, Integer port, Invocation invocation) {
        if (client == null) {
            start(hostName, port);
        }
        client.setInvocation(invocation);
        try {
            return (String) executorService.submit(client).get();
        } catch (Exception e) {

        }
        return null;
    }


}
