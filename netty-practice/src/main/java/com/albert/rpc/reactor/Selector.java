package com.albert.rpc.reactor;

import com.albert.rpc.reactor.bean.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * reactor模式中的Demultiplexer类，提供select（）方法用于从缓冲队列中查找出符合条件的event列表
 *
 * @author yangjunwei
 * @date 2024-06-04
 */
public class Selector {

    /**
     * 链表保存数据
     */
    private final BlockingQueue<Event> EVENT_QUEUE = new LinkedBlockingDeque<>();

    private final Object lock = new Object();

    public List<Event> select() {
        return this.select(0);
    }

    /**
     * 获取队列中的事件，如果为空，则阻塞等待
     *
     * @param timeout 阻塞等待时间
     * @return
     */
    public List<Event> select(long timeout) {
        //wait
        if (timeout > 0) {
            synchronized (lock) {
                if (EVENT_QUEUE.size() <= 0) {
                    try {
                        //阻塞等待timeout
                        //如果有事件进来，可以被唤醒
                        lock.wait(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        List<Event> events = new ArrayList<>();
        //读取队列中的数据
        EVENT_QUEUE.drainTo(events);
        return events;
    }

    /**
     * 接收客户端事件
     * @param event
     */
    public void addEvent(Event event) {
        boolean offer = EVENT_QUEUE.offer(event);
        if (offer) {
            //放入数据成功，唤醒 select
            synchronized (lock) {
                lock.notify();
            }
        }
    }


}
