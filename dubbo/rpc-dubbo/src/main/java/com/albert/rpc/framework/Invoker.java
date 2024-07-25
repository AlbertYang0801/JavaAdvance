package com.albert.rpc.framework;

/**
 * 客户端-服务调用者
 * @author yangjunwei
 * @date 2024/7/25
 */
@FunctionalInterface
public interface Invoker {

    String invoke(Invocation invocation);


}
