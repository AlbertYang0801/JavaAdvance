package com.albert.javase.log;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;

/**
 * @author yangjunwei
 * @date 2024/7/29
 */
public class TraceUtil {

    private static String createTraceId() {
        return "TID:" + UUID.fastUUID();
    }

    public static void setTraceId(String traceId) {
        if (StrUtil.isNotBlank(traceId)) {
            MDC.put("traceId", traceId);
        }
    }

    public static void setTraceId() {
        //Log提供的MDC，可以修改日志文件需要的变量
        MDC.put("traceId", createTraceId());
    }


}
