package com.albert.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.ImmediateEventExecutor;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 性能监控
 * @author yjw
 * @date 2024/6/10 21:42
 */
@Slf4j
public class MetricsHandler extends ChannelDuplexHandler {

    private static AtomicBoolean startTask = new AtomicBoolean(false);
    private static ScheduledExecutorService statService = new ScheduledThreadPoolExecutor(1);

    /**
     * 监控的指标
     */
    private static AtomicLong chCount = new AtomicLong(0);
    private static AtomicLong totalReadBytes = new AtomicLong(0);
    private static AtomicLong totalWriteBytes = new AtomicLong(0);

    /**
     * ChannelGroup用来保存所有已经连接的Channel
     */
    private final static ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        int writeableBytes = ((ByteBuf) msg).readableBytes();
        totalWriteBytes.getAndAdd(writeableBytes);
        super.write(ctx, msg, promise);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        chCount.incrementAndGet();
        if (startTask.compareAndSet(false, true)) {
            statService.scheduleAtFixedRate(() -> {
                log.info("----------------性能指标采集开始-------------------");
                log.info("目前在线Channel数：" + chCount.get());
                // I/O线程池待处理队列大小
                Iterator<EventExecutor> executorGroups = ctx.executor().parent().iterator();
                while (executorGroups.hasNext()) {
                    SingleThreadEventExecutor executor =
                            (SingleThreadEventExecutor) executorGroups.next();
                    int size = executor.pendingTasks();
                    if (executor == ctx.executor()){
                        log.info(ctx.channel() + ":" + executor + "待处理队列大小 :  " + size);
                    }
                    else{
                        log.info(executor + " 待处理队列大小 : " + size);
                    }
                }

                //发送队列积压字节数
                Iterator<Channel> channels = channelGroup.iterator();
                while(channels.hasNext()){
                    Channel channel = channels.next();
                    if(channel instanceof ServerChannel){
                        continue;
                    }
                    log.info(channel+"发送缓存积压字节数："+channel.unsafe().outboundBuffer().totalPendingWriteBytes());
                }

                log.info( "读取速率(字节/分)："+totalReadBytes.getAndSet(0));
                log.info( "写出速率(字节/分)："+totalWriteBytes.getAndSet(0));

                log.info("----------------性能指标采集结束-------------------");
            }, 0, 60 * 1000, TimeUnit.MILLISECONDS);

        }


        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        int readableBytes = ((ByteBuf) msg).readableBytes();
        totalReadBytes.getAndAdd(readableBytes);
        ctx.fireChannelRead(msg);
    }

    /**
     * 不活跃
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        chCount.decrementAndGet();
        channelGroup.remove(ctx.channel());
        super.channelInactive(ctx);
    }


}
