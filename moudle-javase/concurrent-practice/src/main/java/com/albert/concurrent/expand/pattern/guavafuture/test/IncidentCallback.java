package com.albert.concurrent.expand.pattern.guavafuture.test;

/**
 * @author yangjunwei
 * @date 2024/7/3
 */
public interface IncidentCallback<V> {

    void success(V v);

    void failure(Throwable throwable);


}
