package com.albert.javase.jvm.directbuffer;

import org.springframework.boot.origin.SystemEnvironmentOrigin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接内存
 *
 * @author yjw
 * @date 2022/4/6 22:19
 */
public class DirectBuffer {

    private static String FROM = "E:\\你眼中的世界.mp4";
    private static String TO = "E:\\你眼中的世界1.mp4";
    private static String TO2 = "E:\\你眼中的世界2.mp4";

    private static int size = 1024 * 1024;

    public static void main(String[] args) {
        io();
        directBuffer();
    }

    private static void io() {
        long start = System.currentTimeMillis();
        try (FileInputStream from = new FileInputStream(FROM);
             FileOutputStream to = new FileOutputStream(TO)) {
            byte[] bytes = new byte[size];
            while (true){
                int len= from.read(bytes);
                if(len==-1){
                    break;
                }
                to.write(bytes,0,len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("io 用时："+(end-start));
    }

    private static void directBuffer() {
        long start = System.currentTimeMillis();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
             FileChannel to = new FileOutputStream(TO2).getChannel()) {
            ByteBuffer bytes = ByteBuffer.allocateDirect(size);
            while (true){
                int len= from.read(bytes);
                if(len==-1){
                    break;
                }
                bytes.flip();
                to.write(bytes);
                bytes.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("directBuffer 用时："+(end-start));
    }


}
