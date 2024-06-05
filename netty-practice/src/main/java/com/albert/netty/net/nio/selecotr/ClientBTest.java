package com.albert.netty.net.nio.selecotr;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author yjw
 * @date 2024/6/3 16:24
 */
public class ClientBTest {

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream output = null;
        InputStream input = null;

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
        try {
            socket = new Socket();
            socket.connect(inetSocketAddress);
            System.out.println("Connect Success!");

            output = socket.getOutputStream();
            input = socket.getInputStream();

            System.out.println("B ready send msg");

            //发送给服务端
            output.write("zhangsan".getBytes(StandardCharsets.UTF_8));
            output.flush();

            byte[] bytes = new byte[64];
            //读取服务端返回结果
            while (input.read(bytes) != -1) {
                System.out.println("client: " + new String(bytes));
            }
        } finally {
            if (socket != null) {
                socket.close();
            }
            if (input != null) {
                input.close();
            }
            if (output != null) {
                output.close();
            }
        }
    }

}
