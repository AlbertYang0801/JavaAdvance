package com.albert.netty;

import com.albert.netty.server.BootNettyServer;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;

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
