package com.albert.netty.reactor;

import com.albert.netty.reactor.bean.Event;
import com.albert.netty.reactor.bean.EventType;
import com.albert.netty.reactor.bean.InputSource;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author yangjunwei
 * @date 2024-06-04
 */
public class Acceptor implements Runnable {

    private int port;

    private Selector selector;

    private BlockingQueue<InputSource> sourceQueue = new LinkedBlockingDeque<>();

    public Acceptor(Selector selector, int port) {
        this.port = port;
        this.selector = selector;
    }

    public void addNewConnection(InputSource inputSource) {
        sourceQueue.offer(inputSource);
    }

    public int getPort() {
        return this.port;
    }


    @Override
    public void run() {
        //模拟 Socket.accept()
        while (true) {
            try {
                //相当于 Serversocket.accept()，接收输入请求，该例从请求队列中获取输入请求
                InputSource inputSource = sourceQueue.take();

                //向 selector 添加 event
                Event event = new Event();
                event.setInputSource(inputSource);
                event.setEventType(EventType.ACCEPT);
                selector.addEvent(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
