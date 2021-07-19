package com.albert.redis.service;

import com.albert.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 四、set 集合实战 - 可能认识的人/共同关注的人
 *
 * @author yangjunwei
 * @date 2021/7/19 7:18 下午
 */
@Service
public class PersonService {

    @Autowired
    RedisUtil redisUtil;

    private final String PERSON_KEY = "person:";

    /**
     * 共同认识的人
     */
    public Set<String> commonConcern(String userIdA, String userIdB) {
        //取交集
        return redisUtil.sIntersect(PERSON_KEY + userIdA, PERSON_KEY + userIdB);
    }

    /**
     * 可能认识的人
     */
    public Set<String> mayKnow(String userIdA, String userIdB) {
        //取差集
        return redisUtil.sDifference(PERSON_KEY + userIdA, PERSON_KEY + userIdB);
    }


}
