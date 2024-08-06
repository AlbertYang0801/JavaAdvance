package com.albert.rpc.framework;

import java.util.List;
import java.util.Random;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
public class LoadBalance {

    /**
     * 负载均衡-随机
     * @param list
     * @return
     */
    public static Invoker random(List<Invoker> list) {
        Random random = new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }


}
