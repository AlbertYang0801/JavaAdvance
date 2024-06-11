package com.albert.rpc.netty.frame;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.rpc.netty.frame.client.ClientInit;
import com.albert.rpc.netty.frame.vo.NettyConstant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yangjunwei
 * @date 2024-06-11
 */
@Slf4j
@Service
public class RpcClientFrame implements Runnable {

    private EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;

    //负责重连的线程池
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    /*是否用户主动关闭连接的标志值*/
    private volatile boolean userClose = false;
    /*连接是否成功关闭的标志值*/
    private volatile boolean connected = false;

    private volatile AtomicLong retryConnectCount = new AtomicLong(0);

    private int maxRetry = 2;

    @Autowired
    private ClientInit clientInit;

    public void connect(String host, int port) throws InterruptedException {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(clientInit);
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            log.info("已经连接服务器");
            channel = future.sync().channel();
            //连接成功后通知等待线程，连接已经建立
            synchronized (this) {
                this.connected = true;
                //通知业务client
                this.notifyAll();
            }
            channel.closeFuture().sync();
        } finally {
            //非用户主动关闭，说明发生了网络问题，需要进行重连操作*
            if (!userClose) {
                log.info("发现异常，可能发生了服务器异常或网络问题，准备进行重连......");
                long andIncrement = retryConnectCount.getAndIncrement();
                if (andIncrement > maxRetry) {
                    log.warn("达到最大重试次数 {}，放弃连接!",maxRetry);
                    retryConnectCount.set(0);
                }else{
                    executor.submit(() -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                            //重连
                            connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else {
                //正常关闭，不需要通知，改状态即可
                channel = null;
                group.shutdownGracefully().sync();
                this.connected = false;
//                synchronized (this) {
//                    this.connected = false;
//                    this.notifyAll();
//                }
            }
        }
    }

    @Override
    public void run() {
        try {
            connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        userClose = true;
        channel.close();
    }

    public boolean isConnected() {
        return connected;
    }

    @PostConstruct
    public void startNetty() throws InterruptedException {
        ThreadUtil.newSingleExecutor().submit(this);
        //等待其余线程断开连接
        while (!this.isConnected()) {
            synchronized (this) {
                //手动唤醒
                this.wait();
            }
        }
        log.info("网络通信已准备好，可以进行业务操作了........");
    }

    @PreDestroy
    public void stopNetty() {
        log.info("PreDestory销毁客户端");
        close();
    }


}