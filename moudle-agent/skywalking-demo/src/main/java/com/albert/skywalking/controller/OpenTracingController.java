package com.albert.skywalking.controller;

import io.opentracing.Tracer;
import org.apache.skywalking.apm.toolkit.opentracing.SkywalkingTracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangjunwei
 * @date 2024/8/19
 */
@RestController
@RequestMapping("/opentracing")
public class OpenTracingController {

    @GetMapping("/opentracing")
    public String echo() {
        //使用 opentracing的API 创建一个Span
        Tracer tracer = new SkywalkingTracer();
        tracer.buildSpan("custom_operation").withTag("mp","skywalking-test").startManual().finish();
        return "openTracing";
    }


}
