package com.albert.netty;

import com.albert.netty.client.ClientInit;
import com.albert.netty.vo.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yjw
 * @date 2024/6/10 16:53
 */
@Slf4j
public class NettyClient implements Runnable {

    private EventLoopGroup group = new NioEventLoopGroup();
    private Channel channel;
    //负责重连的线程池
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    /*是否用户主动关闭连接的标志值*/
    private volatile boolean userClose = false;
    /*连接是否成功关闭的标志值*/
    private volatile boolean connected = false;

    public void connect(String host, int port) throws InterruptedException {
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInit());
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(host, port)).sync();
            log.info("已经连接服务器");
            channel = future.channel();
            synchronized (this) {
                this.connected = true;
                //通知业务client
                this.notifyAll();
            }
            channel.closeFuture().sync();
        } finally {
            if (!userClose) {
                log.info("需要手动进行重连");
                executor.submit(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                        connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                //正常关闭
                channel = null;
                group.shutdownGracefully().sync();
                synchronized (this) {
                    this.connected = false;
                    this.notifyAll();
                }
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

    public static void main(String[] args) throws InterruptedException {
        NettyClient nettyClient = new NettyClient();
        //connect
        nettyClient.connect(NettyConstant.SERVER_IP, NettyConstant.SERVER_PORT);
        nettyClient.send("v");
        nettyClient.close();
    }

    public void send(Object message) {
        if (channel == null || !channel.isActive()) {
            throw new IllegalStateException("和服务器还未未建立起有效连接！请稍后再试！！");
        }
        MyMessage msg = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setMsgId(MakeMsgID.getID());
        msgHeader.setType(MessageType.SERVICE_REQ.value());
        msgHeader.setMd5(EncryptUtils.encryptObj(message));
        msg.setMsgHeader(msgHeader);
        msg.setBody(message);
        channel.writeAndFlush(msg);
    }

    public void sendOneWay(Object message) {
        if (channel == null || !channel.isActive()) {
            throw new IllegalStateException("和服务器还未未建立起有效连接！" +
                    "请稍后再试！！");
        }
        MyMessage msg = new MyMessage();
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setMsgId(MakeMsgID.getID());
        msgHeader.setType(MessageType.ONE_WAY.value());
        msgHeader.setMd5(EncryptUtils.encryptObj(message));
        msg.setMsgHeader(msgHeader);
        msg.setBody(message);
        channel.writeAndFlush(msg);
    }

    public void close() {
        userClose = true;
        channel.close();
    }

    public boolean isConnected() {
        return connected;
    }

}
