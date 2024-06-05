package com.albert.netty.net.nio.channel;

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
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //读取文件到缓冲区
        while (channel.read(byteBuffer) != -1) {
            //切换缓冲区为读模式
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()){
                System.out.println((char)byteBuffer.get());
            }
            //清空缓冲区
            byteBuffer.clear();
        }


    }


}
