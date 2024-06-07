package com.albert.netty.http.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * 自定义 ChannelSocket
 * 处理请求的数据
 *
 * @author yangjunwei
 * @date 2024-06-06
 */
public class BusiHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        System.out.println(httpRequest.headers());
        try {
            String uri = httpRequest.uri();
            String body = httpRequest.content().toString(CharsetUtil.UTF_8);

            HttpMethod method = httpRequest.method();
            System.out.println("接收到" + method.name() + "请求");

            if (HttpMethod.GET.equals(method)) {
                //接受到的消息，做业务逻辑处理...
                System.out.println("body:" + body);
                String result = "GET请求,应答:" + RespConstant.getNews();
                send(ctx, result, HttpResponseStatus.OK);
                return;
            }

        } finally {
            //释放请求资源
            httpRequest.release();
        }
    }

    private void send(ChannelHandlerContext ctx, String result, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,status,
                Unpooled.copiedBuffer(result,CharsetUtil.UTF_8)
        );
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=UTF-8");
        //返回
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
    }


}
