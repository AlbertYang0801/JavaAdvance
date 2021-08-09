package com.albert.redis.datastructure;

import com.albert.redis.utils.RedisUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 六、bitmaps 数据结构实战 - 用户登录状态
 *
 * @author yangjunwei
 * @date 2021/8/9 9:40 上午
 */
@Service
public class Bitmaps_SignInService {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisTemplate redisTemplate;

    private final String SIGNIN = "signIN";

    /**
     * 用户签到
     */
    public boolean signIn(String userId) {
        //当月
        String month = LocalDateTimeUtils.formatNow(LocalDateTimeUtils.YEAR_MONTH);
        //本月第几天
        int dayOfMonth = LocalDateTime.now().getDayOfMonth();
        return redisUtil.setBit(getSignInKey(userId, month), dayOfMonth, true);
    }

    /**
     * 获取指定用户某月份签到总数
     */
    public int countMonthSignIn(String userId, String month) {
        String signInKey = getSignInKey(userId, month);
        //redisTemplate没有提供bitcount命令，可使用execute调用相关方法
        Long execute = (Long) redisTemplate.execute(
                (RedisCallback<Long>) con -> con.bitCount(signInKey.getBytes()));
        if (Objects.isNull(execute)) {
            return 0;
        }
        return execute.intValue();
    }

    private String getSignInKey(String userId, String month) {
        return SIGNIN + ":" + month + ":" + userId;
    }


}
