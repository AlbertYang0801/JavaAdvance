package com.albert.netty.reactor.event;

import com.albert.netty.reactor.Selector;
import com.albert.netty.reactor.bean.Event;
import com.albert.netty.reactor.bean.EventType;
import com.albert.netty.reactor.bean.InputSource;

/**
 * @author yjw
 * @date 2024/6/5 22:05
 */
public class ReadEventHandler extends EventHandler {

    Selector selector;

    public ReadEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
       if(!event.getEventType().equals(EventType.READ)){
           return;
       }
        InputSource inputSource = event.getInputSource();
        Object data = inputSource.getData();
        System.out.println("read obj "+data.toString());
    }



}
