package com.albert.netty.server.async;

import com.albert.netty.vo.MyMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yjw
 * @date 2024/6/10 21:21
 */
@Slf4j
public class DefaultTaskProcessor implements ITaskProcessor {

    @Override
    public Runnable execAsyncTask(MyMessage msg) {
        return () -> log.info("DefaultTaskProcessor模拟任务处理：" + msg.getBody());
    }


}
