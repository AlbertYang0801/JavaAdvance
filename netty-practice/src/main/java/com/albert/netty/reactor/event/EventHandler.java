package com.albert.netty.reactor.event;

import com.albert.netty.reactor.bean.InputSource;
import com.albert.netty.reactor.bean.Event;

/**
 * 事件处理器的抽象类
 * @author yangjunwei
 * @date 2024-06-04
 */
public abstract class EventHandler {

    /**
     * 事件处理
     * @param event
     */
    public abstract void handle(Event event);

    private InputSource inputSource;


}
