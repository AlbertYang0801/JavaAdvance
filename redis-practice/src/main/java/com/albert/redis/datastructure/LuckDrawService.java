package com.albert.redis.datastructure;

import com.albert.redis.utils.RedisUtil;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 四、set 集合实战 - 抽奖
 *
 * @author yangjunwei
 * @date 2021/7/19 7:06 下午
 */
@Service
public class LuckDrawService {

    @Autowired
    RedisUtil redisUtil;

    private final String LUCK_DRAW = "luckDraw";

    public Set<String> getLuckDraw(){
        return redisUtil.setMembers(LUCK_DRAW);
    }

    /**
     * 参加抽奖
     *
     * @param userId 用户Id
     */
    public void join(String userId) {
        redisUtil.sAdd(LUCK_DRAW, userId);
    }

    /**
     * 统计抽奖人数
     */
    public long count() {
        return redisUtil.sSize(LUCK_DRAW);
    }

    /**
     * 抽奖 - 不限制次数
     *
     * @param num 中奖总人数
     */
    public List<String> pump(int num) {
        return redisUtil.sRandomMembers(LUCK_DRAW, num);
    }

    /**
     * 抽奖 - 限制只能抽一次
     * @param num 中奖人数
     */
    public List<String> pumpSingle(int num) {
        List<String> userIdList = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            userIdList.add(redisUtil.sPop(LUCK_DRAW));
        }
        return userIdList;
    }


}
