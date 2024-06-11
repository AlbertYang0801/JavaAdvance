package com.albert.rpc.server.async;

import com.albert.rpc.vo.MyMessage;

/**
 * @author yjw
 * @date 2024/6/10 21:21
 */
public interface ITaskProcessor {

    Runnable execAsyncTask(MyMessage msg);

}
