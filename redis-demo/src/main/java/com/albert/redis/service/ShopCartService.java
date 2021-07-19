package com.albert.redis.service;

import com.albert.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 三、hash 数据结构实战 - 购物车
 *
 * @author yangjunwei
 * @date 2021/7/19 7:43 下午
 */
@Service
public class ShopCartService {

    @Autowired
    RedisUtil redisUtil;

    private final String SHOP_CART = "shopCart:";

    /**
     * 向购物车增加商品
     *
     * @param userId  用户Id
     * @param sellId  商品Id
     * @param shopNum 购买数量
     */
    public void addSell(String userId, String sellId, Integer shopNum) {
        redisUtil.hPut(SHOP_CART + userId, sellId, String.valueOf(shopNum));
    }

    /**
     * 增加购物车某个商品的数量
     *
     * @param userId     用户Id
     * @param sellId     商品Id
     * @param addShopNum 增加数量
     */
    public void addSellNum(String userId, String sellId, Integer addShopNum) {
        redisUtil.hIncrBy(SHOP_CART + userId, sellId, addShopNum);
    }

    /**
     * 获取购物车商品总数
     *
     * @param userId 用户Id
     */
    public long countShopCartSell(String userId) {
        return redisUtil.hSize(SHOP_CART + userId);
    }

    /**
     * 支持批量删除某个商品
     *
     * @param userId     用户Id
     * @param sellIdList 商品Id
     */
    public void delSell(String userId, List<String> sellIdList) {
        redisUtil.hDelete(SHOP_CART + userId, sellIdList);
    }

    /**
     * 获取购物车列表
     *
     * @param userId 用户Id
     */
    public Map<Object, Object> getShopCartList(String userId) {
        return redisUtil.hGetAll(SHOP_CART + userId);
    }


}
