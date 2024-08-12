package com.albert.rpc.reactor.event;


import com.albert.rpc.reactor.Selector;
import com.albert.rpc.reactor.bean.Event;
import com.albert.rpc.reactor.bean.EventType;

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
            System.out.println("处理accept事件");
            //将事件改为 READ，并放入 Selecotr 队列中，继续处理
            Event readEvent = new Event();
            readEvent.setInputSource(event.getInputSource());
            readEvent.setEventType(EventType.READ);
            //添加到 Selector 中
            selector.addEvent(event);
        }
    }


}
