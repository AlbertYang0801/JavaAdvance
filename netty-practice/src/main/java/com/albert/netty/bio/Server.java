package com.albert.netty.bio;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO-服务端
 *
 * @author yangjunwei
 * @date 2024-05-29
 */
@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        //客户端绑定 socket 端口
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1",9998));

        try {
            while (true){
                //accept() 监听指定端口的请求，处于阻塞状态
                new Thread(new ServerTask(serverSocket.accept()));
            }
        }finally {
            serverSocket.close();
        }

    }


    private static class ServerTask implements Runnable {

        private Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @SneakyThrows
        @Override
        public void run() {
            //与客户端通信
            try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
                String userName = objectInputStream.readUTF();
                System.out.println("userName:" + userName);

                objectOutputStream.writeUTF("Hello " + userName);
                objectOutputStream.flush();
            } catch (Exception e) {
                log.error("server error:", e);
            } finally {
                if (socket != null) {
                    socket.close();
                }
            }
        }
    }


}

