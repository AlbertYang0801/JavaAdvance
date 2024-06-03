package com.albert.netty.nio.selecotr;

import lombok.SneakyThrows;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * @author yjw
 * @date 2024/6/3 15:31
 */
public class SelectorTest {

    @SneakyThrows
    public static void main(String[] args) {
        //Selector
        Selector selector = Selector.open();
        //Channel
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        //阻塞模式
        socketChannel.configureBlocking(false);
        socketChannel.socket().bind(new InetSocketAddress("127.0.0.1",9999),1024);
        //Selector 管理 Channel
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);


    }


}
