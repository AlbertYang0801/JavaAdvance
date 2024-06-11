package com.albert.rpc.reactor;

import cn.hutool.core.thread.ThreadUtil;
import com.albert.rpc.reactor.bean.Event;
import com.albert.rpc.reactor.bean.EventType;
import com.albert.rpc.reactor.event.EventHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * reactor模式中Dispatcher类，负责event的分发和eventHandler的维护
 *
 * @author yangjunwei
 * @date 2024-06-04
 */
public class Dispatcher {

    /**
     * 维护事件处理器
     */
    private Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<>();

    private Selector selector;

    /**
     * 依赖 Selector
     *
     * @param selector
     */
    Dispatcher(Selector selector) {
        this.selector = selector;
    }

    /**
     * 不同事件类型，选择不同的事件处理器
     *
     * @param eventType
     * @param eventHandler
     */
    public void registerEventHandler(EventType eventType, EventHandler eventHandler) {
        eventHandlerMap.put(eventType,eventHandler);
    }

    public void removeEventHandler(EventType eventType) {
        eventHandlerMap.remove(eventType);
    }

    public void handlerEvents(){
        ExecutorService executorService = ThreadUtil.newExecutor(1);
        //同步非阻塞
        //线程池异步提交
        executorService.execute(this::dispatch);
    }

    public void dispatch(){
        while (true){
            //从 selector 中阻塞获取数据
            List<Event> events = selector.select();
            for (Event event : events) {
                //分发处理
                EventHandler eventHandler = eventHandlerMap.getOrDefault(event.getEventType(),null);
                if(Objects.isNull(eventHandler)){
                    continue;
                }
                //处理事件
                eventHandler.handle(event);
            }
        }
    }



}
