package com.albert.spring.filter.controller;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Albert
 * @date 2020/9/7 17:15
 */
@Slf4j
@RestController
@RequestMapping("/filter")
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
        log.info(JSONUtil.toJsonStr(map));
        return new ResponseResult(map,true,"获取成功");
    }


}
