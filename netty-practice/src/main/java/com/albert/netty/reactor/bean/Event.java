package com.albert.netty.reactor.bean;

import lombok.Data;

/**
 * 事件
 * reactor模式的事件类，可以理解为将输入原始对象根据不同状态包装成一个事件类，reactor模式里处理的就是event事件对象
 * @author yangjunwei
 * @date 2024-06-04
 */
@Data
public class Event {

    private InputSource inputSource;

    private EventType eventType;


}
