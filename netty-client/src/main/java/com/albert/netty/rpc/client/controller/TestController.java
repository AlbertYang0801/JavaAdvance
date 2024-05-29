package com.albert.netty.rpc.client.controller;

import com.albert.netty.rpc.server.service.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yjw
 * @date 2024/5/29 23:02
 */
@RestController
public class TestController {

    @Autowired
    IStockService iStockService;

    @GetMapping("/test")
    public long getStock(@RequestParam("skuId")Integer skuId){
        return iStockService.getStock(skuId);
    }

}
