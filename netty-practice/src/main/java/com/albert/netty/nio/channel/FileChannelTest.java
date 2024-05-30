package com.albert.netty.nio.channel;

import lombok.SneakyThrows;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yangjunwei
 * @date 2024-05-30
 */
public class FileChannelTest {

    @SneakyThrows
    public static void main(String[] args) {
        String path = "D:\\IdeaWorkSpace\\JavaAdvanced\\netty-practice\\file\\test.txt";
        File file = new File(path);
        RandomAccessFile raf = new RandomAccessFile(file, "r");

        FileChannel channel = raf.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        channel.read(allocate);


    }


}
