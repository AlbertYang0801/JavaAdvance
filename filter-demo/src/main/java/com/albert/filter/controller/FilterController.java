package com.albert.filter.controller;

import com.albert.utils.jackson.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Albert
 * @date 2020/9/7 17:15
 */
@Slf4j
@RestController
public class FilterController {


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResponseResult {
        private Object data;
        private Boolean success;
        private String msg;
    }

    @GetMapping("/filter")
    public ResponseResult getRequestParam(@RequestParam Map<String,Object> map){
        log.info(JsonUtil.toString(map));
        return new ResponseResult(map,true,"获取成功");
    }
}
