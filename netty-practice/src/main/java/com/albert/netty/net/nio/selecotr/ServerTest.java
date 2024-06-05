package com.albert.netty.net.nio.selecotr;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author yjw
 * @date 2024/6/3 15:36
 */
public class ServerTest {

    /**
     * 使用 Selecotr 监听两个 Channel
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Selector
            Selector selector = Selector.open();

            //ChannelA
            ServerSocketChannel socketChannelA = ServerSocketChannel.open();
            socketChannelA.configureBlocking(false);
            socketChannelA.socket().bind(new InetSocketAddress("127.0.0.1", 9999), 1024);

            //Selector 管理 Channel
            //监听ACCEPT事件
            socketChannelA.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                System.out.println("等待事件发生");
                //监听Selector，判断是否有ACCEPT或者READ事件，有的话就会将Channel对应的SelectedKey加入到集合中
                selector.select();
                System.out.println("有事件发生了");

                //遍历就绪的 SelectedKey
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    //1.先监听ACCEPT事件
                    //2.添加客户端Channel对应的SelectedKey到 keys,Selecotr触发READ事件
                    handler(next);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handler(SelectionKey selectionKey) throws IOException {
        if (selectionKey.isAcceptable()) {
            System.out.println("连接事件发生");

            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();

            //2.监听到ACCEPT事件后，获取客户端连接Channel
            //获取连接
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            // 客户端Channel可以读数据，添加到 Selector中
            // 注册Read事件
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            //3.监听到READ事件
            System.out.println("可读事件发生");
            //获取到客户端连接
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

            //读取到客户端发送的内容
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取过程是阻塞的（返回读取数据的长度）
            int len = socketChannel.read(buffer);
            String reqContent = new String(buffer.array(), 0, len);
            if(len!=-1){
                System.out.println("读取到客户端发送的数据:" + new String(buffer.array(), 0, len));
            }

            //写入客户端Channel
            //warp 把字节包装成 byteBuffer
            ByteBuffer wrap = ByteBuffer.wrap((reqContent + " hello world").getBytes(StandardCharsets.UTF_8));
            socketChannel.write(wrap);
            selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            //关闭客户端连接
            socketChannel.close();
        }
    }


}
