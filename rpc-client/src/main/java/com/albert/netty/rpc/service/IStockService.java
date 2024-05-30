package com.albert.netty.rpc.service;

/**
 * 远程服务接口
 * @author yjw
 * @date 2024/5/29 21:36
 */
public interface IStockService {

    /**
     * 获取商品的库存
     * @param skuId
     * @return
     */
    long getStock(Integer skuId);


}
