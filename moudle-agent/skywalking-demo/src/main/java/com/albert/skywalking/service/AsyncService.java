package com.albert.skywalking.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2024/8/19
 */
@Service
public class AsyncService {

    @Async
    public void demo(){
        System.out.println("异步任务执行");
    }


}
