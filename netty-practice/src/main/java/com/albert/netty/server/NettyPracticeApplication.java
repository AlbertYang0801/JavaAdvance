package com.albert.netty.server;

import com.albert.netty.server.BootNettyServer;
import lombok.SneakyThrows;

/**
 * @author yjw
 * @date 2024/3/26 23:24
 */
public class NettyPracticeApplication{

    @SneakyThrows
    public static void main(String[] args ) {
        new BootNettyServer().bind(8088);
    }




}