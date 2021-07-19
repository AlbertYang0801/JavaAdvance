package com.albert.redis.service;

import com.albert.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 四、set 集合实战 - 景点预约
 *
 * @author yangjunwei
 * @date 2021/7/18 9:55 下午
 */
@Service
public class ReservationService {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 添加预约 - sadd
     *
     * @param attractionName 景区名称
     * @param userId         用户id
     */
    public void add(String attractionName, String userId) {
        redisUtil.sAdd(attractionName, userId);
    }

    /**
     * 取消预约 - srem
     *
     * @param attractionName 景区名称
     * @param userId         用户id
     */
    public void delete(String attractionName, String userId) {
        redisUtil.sRemove(attractionName, userId);
    }

    /**
     * 获取预约总人数 - scard
     *
     * @param attractionName 景区名称
     * @return 总人数
     */
    public Long count(String attractionName) {
        return redisUtil.sSize(attractionName);
    }

    /**
     * 判断某个用户是否预约过 - sismember
     *
     * @param attractionName 景区名称
     * @param userId         用户id
     * @return 预约结果
     */
    public boolean isReservation(String attractionName, String userId) {
        return redisUtil.sIsMember(attractionName, userId);
    }

    /**
     * 展示预约客户 - smembers
     *
     * @param attractionName 景区名称
     * @return 预约客户id
     */
    public Set<String> reservationDetails(String attractionName) {
        return redisUtil.setMembers(attractionName);
    }


}
