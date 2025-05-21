package com.albert.boot.ResponseBodyEmitter;

/**
 * @author yangjunwei
 * @date 2025/4/18 18:01
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * 实时响应流
 */
@RestController
@RequestMapping("/api/log")
public class LogController {
    @GetMapping("/stream")
    public ResponseEntity<ResponseBodyEmitter> handleStreamWithHeaders() {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        // 异步发送数据
        Executors.newSingleThreadExecutor().submit(() -> {
            try {
                // 发送JSON数据
                ObjectMapper mapper = new ObjectMapper();
                for (int i = 0; i < 55; i++) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("value", i);
                    data.put("timestamp", System.currentTimeMillis());

                    emitter.send(mapper.writeValueAsString(data) + "\n");
                    Thread.sleep(500);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_NDJSON);
        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }


}