package com.albert.netty.net.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author yangjunwei
 * @date 2024-05-29
 */
@Slf4j
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ObjectInputStream input = null;
        ObjectOutputStream output = null;

        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9998);
        try {
            socket = new Socket();
            socket.connect(inetSocketAddress);
            System.out.println("Connect Success!");

            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

            System.out.println("ready send msg");

            output.writeUTF("zhangsan");
            output.flush();

            String content = input.readUTF();
            System.out.println("client:" + content);
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
