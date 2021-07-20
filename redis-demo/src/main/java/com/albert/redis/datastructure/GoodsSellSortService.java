package com.albert.redis.datastructure;

import com.albert.redis.entry.SellScore;
import com.albert.redis.utils.RedisUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 五、zset 数据结构实战 - 商品销售排行榜
 *
 * @author yangjunwei
 * @date 2021/7/19 7:26 下午
 */
@Service
public class GoodsSellSortService {

    @Autowired
    RedisUtil redisUtil;

    private final String GOODS_SELL = "goodsSell";

    /**
     * 增加商品销量
     */
    public void addGoodsSell(String goodsId, Integer nums) {
        redisUtil.zIncrementScore(GOODS_SELL, goodsId, nums);
    }

    /**
     * 查询销量top的商品和销量
     */
    public List<SellScore> goodsSellSortTop(Integer top) {
        List<SellScore> sellScoreList = Lists.newArrayList();
        Set<ZSetOperations.TypedTuple<String>> typedTuples = redisUtil.zReverseRangeWithScores(GOODS_SELL, 0, top);
        for (ZSetOperations.TypedTuple<String> typedTuple : typedTuples) {
            SellScore sellScore = new SellScore();
            sellScore.setSellValue(typedTuple.getValue());
            sellScore.setScore(typedTuple.getScore());
            sellScoreList.add(sellScore);
        }
        return sellScoreList;
    }


}
