package com.albert.netty.reactor.event;


import com.albert.netty.reactor.Selector;
import com.albert.netty.reactor.bean.Event;
import com.albert.netty.reactor.bean.EventType;

/**
 * 处理 accept 事件
 *
 * @author yangjunwei
 * @date 2024-06-04
 */
public class AcceptEventHandler extends EventHandler {

    private Selector selector;

    public AcceptEventHandler(Selector selector){
        this.selector = selector;
    }

    @Override
    public void handle(Event event) {
        // 处理accept事件
        if (event.getEventType().equals(EventType.ACCEPT)) {
            //将事件改为 READ，并放入 Selecotr 队列中，继续处理
            Event readEvent = new Event();
            readEvent.setInputSource(event.getInputSource());
            readEvent.setEventType(EventType.READ);
            //添加到 Selector 中
            selector.addEvent(event);
        }
    }


}
