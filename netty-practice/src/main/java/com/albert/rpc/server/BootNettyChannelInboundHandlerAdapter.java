package com.albert.rpc.server;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;
import lombok.SneakyThrows;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URLEncoder;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * IO数据读写类
 *
 * @author yjw
 * @date 2024/3/26 23:29
 */
@ChannelHandler.Sharable
public class BootNettyChannelInboundHandlerAdapter extends SimpleChannelInboundHandler<FullHttpRequest> {

    private FullHttpRequest request;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        this.request = fullHttpRequest;
        System.out.println("请求进来了");
        String uri = request.uri();
        if (uri.contains("down")) {
            downloadFile(channelHandlerContext);
        }
    }

    /*连接建立以后*/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "Hello Netty", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


    @SneakyThrows
    public void downloadFile(ChannelHandlerContext ctx) {
        String path = "D:\\IdeaWorkSpace\\JavaAdvanced\\netty-practice\\file\\test.txt";
        File file = new File(path);
        RandomAccessFile raf;
        raf = new RandomAccessFile(file, "r");
        long fileLength = raf.length();

        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
        HttpUtil.setContentLength(response, fileLength);
        //设置请求头部
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, fileLength);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/octet-stream; charset=UTF-8");
        response.headers().add(HttpHeaderNames.CONTENT_DISPOSITION,
                "attachment; filename=\"" + URLEncoder.encode(file.getName(), "UTF-8") + "\";");
        ctx.write(response);

        // Write the content.
        ChannelFuture sendFileFuture;
        ChannelFuture lastContentFuture;
//        if (ctx.pipeline().get(SslHandler.class) == null) {
//            sendFileFuture = ctx.write(new DefaultFileRegion(raf.getChannel(), 0, fileLength), ctx.newProgressivePromise());
//            // Write the end marker.
//            lastContentFuture = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
//        } else {
            sendFileFuture = ctx.writeAndFlush(new HttpChunkedInput(new ChunkedFile(raf, 0, fileLength, 8192)),
                            ctx.newProgressivePromise());
            // HttpChunkedInput will write the end marker (LastHttpContent) for us.
            lastContentFuture = sendFileFuture;
//        }

        sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) {
                if (total < 0) { // total unknown
                    System.err.println(future.channel() + " Transfer progress: " + progress);
                } else {
                    System.err.println(future.channel() + " Transfer progress: " + progress + " / " + total);
                }
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) {
                System.err.println(future.channel() + " Transfer complete.");
            }
        });

        //刷新缓冲区数据，文件结束标志符
        ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
    }


}
