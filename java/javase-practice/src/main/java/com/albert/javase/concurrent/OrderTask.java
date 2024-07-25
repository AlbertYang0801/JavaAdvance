package com.albert.javase.concurrent;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author yangjunwei
 * @date 2024-06-30
 */
@Data
public class OrderTask implements Delayed {

    private String orderId;

    private Long expire;

    private Long time;

    public OrderTask(String orderId, Long expire, Long time) {
        this.orderId = orderId;
        this.expire = expire;
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
       return expire;
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        long diff = getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }
}
