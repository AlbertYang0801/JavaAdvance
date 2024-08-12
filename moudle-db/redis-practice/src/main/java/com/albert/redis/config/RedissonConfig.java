package com.albert.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangjunwei
 * @date 2021/7/23 5:25 下午
 */
@Configuration
public class RedissonConfig {

    //@Bean
    //public RedissonClient redissonClient() {
    //    Config config = new Config();
    //    config.useSingleServer().setAddress("redis://127.0.0.1:6379");
    //    return Redisson.create(config);
    //}

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        // 添加集群地址
        config.useClusterServers().addNodeAddress("redis://10.10.101.68:6370", "redis://10.10.101.69:6370", "redis://10.10.101.148:6370");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }


}
