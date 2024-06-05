package com.albert.netty.reactor.bean;

import lombok.Data;

/**
 * 原始输入对象
 * 外部输入类，用来表示需要reactor去处理的原始对象
 * @author yangjunwei
 * @date 2024-06-04
 */
@Data
public class InputSource {

    private Object data;

    private long id;


}
