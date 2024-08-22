package com.albert.skywalking.controller;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/8/19
 */
@RestController
@RequestMapping("/trace")
public class TraceController {

    @GetMapping("/trace_annotations")
    @Trace(operationName = "trace_annotations")
    public String echo() {
        // <X> 自定义 SkyWalking Span
        ActiveSpan.tag("mp", "测试源码项目");
        // 返回
        return "trace_annotations";
    }


}
