package com.albert.netty.reactor;

import com.albert.netty.reactor.bean.EventType;
import com.albert.netty.reactor.bean.InputSource;
import com.albert.netty.reactor.event.AcceptEventHandler;
import com.albert.netty.reactor.event.ReadEventHandler;

import java.util.Objects;

/**
 * @author yjw
 * @date 2024/6/5 21:57
 */
public class Server {

    private int port;

    Selector selector = new Selector();

    Dispatcher dispatcher = new Dispatcher(selector);

    Acceptor acceptor;

    Server(int port) {
        acceptor = new Acceptor(selector,port);
    }

    public void requestEvent(InputSource inputSource){
        if(Objects.nonNull(acceptor)){
            acceptor.addNewConnection(inputSource);
        }
    }

    public void start() {
        dispatcher.registerEventHandler(EventType.ACCEPT,new AcceptEventHandler(selector));
        dispatcher.registerEventHandler(EventType.READ,new ReadEventHandler(selector));
        //开启accept事件监听
        new Thread(acceptor).start();
        //启动dispatcher轮询事件
        dispatcher.handlerEvents();
    }


}
