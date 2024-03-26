package com.albert.netty.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * @author yjw
 * @date 2024/3/26 23:28
 */
public class BootNettyChannelInitializer<SocketChannel> extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast("http-decoder", new HttpRequestDecoder());
        channel.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
        channel.pipeline().addLast("http-encoder", new HttpResponseEncoder());
        channel.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
        /**
         * 自定义ChannelInboundHandlerAdapter
         */
        channel.pipeline().addLast(new BootNettyChannelInboundHandlerAdapter());
    }

}
