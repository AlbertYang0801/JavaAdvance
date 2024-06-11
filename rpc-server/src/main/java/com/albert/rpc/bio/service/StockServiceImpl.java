package com.albert.rpc.bio.service;

/**
 * @author yjw
 * @date 2024/5/29 21:37
 */
public class StockServiceImpl implements IStockService {
    @Override
    public long getStock(Integer skuId) {
        return skuId + 100;
    }
}
