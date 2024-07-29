package com.albert.concurrent.threadlocal.ttl;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import lombok.extern.slf4j.Slf4j;

/**
 * TransmittableThreadLocal跨线程传递值
 * @author yangjunwei
 * @date 2024/7/29
 */
@Slf4j
public class TtlTest {

    ThreadLocal<String> originThreadLocal = new InheritableThreadLocal<>();

    //跨线程复用
    TransmittableThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

    public void trace(){
        originThreadLocal.set("origin");
        threadLocal.set("traceId");

        log.info("origin-父线程:{}", originThreadLocal.get());
        log.info("父线程:{}", threadLocal.get());

        ThreadUtil.execAsync(()->{
            log.info("origin-子线程:{}", originThreadLocal.get());
            log.info("子线程:{}", threadLocal.get());

            ThreadUtil.execAsync(()->{
                log.info("origin-子线程的子线程:{}", originThreadLocal.get());
                //子线程的子线程依然可以获取到父线程的threadLocl的值
                log.info("子线程的子线程:{}", threadLocal.get());
            });

        });
    }

    public static void main(String[] args) {
        new TtlTest().trace();
    }


}
