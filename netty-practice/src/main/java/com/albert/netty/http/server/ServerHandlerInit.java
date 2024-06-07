package com.albert.netty.http.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author yangjunwei
 * @date 2024-06-06
 */
public class ServerHandlerInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //TODO ssl

        //服务端
        //对请求内容解码
        pipeline.addLast("decoder", new HttpRequestDecoder());
        //对响应内容编码
        pipeline.addLast("encoder", new HttpResponseEncoder());

        //聚合 http 为一个完整的报文
        pipeline.addLast("aggregator", new HttpObjectAggregator(10 * 1024 * 1024));
        //压缩应答报文
        pipeline.addLast("compressor", new HttpContentCompressor());

        //自定义 ChannelHandler
        pipeline.addLast(new BusiHandler());

    }


}
