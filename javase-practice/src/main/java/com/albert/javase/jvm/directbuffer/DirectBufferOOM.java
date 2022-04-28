package com.albert.javase.jvm.directbuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * 直接内存的OOM
 * @author yjw
 * @date 2022/4/6 22:44
 */
public class DirectBufferOOM {

    static int size = 100 * 1024 * 1024;

    public static void main(String[] args) {
        List<ByteBuffer> list = new ArrayList<>();
        int i = 0;
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(size);
                list.add(byteBuffer);
                i++;
            }
        } finally {
            System.out.println(i);
        }
    }


}
