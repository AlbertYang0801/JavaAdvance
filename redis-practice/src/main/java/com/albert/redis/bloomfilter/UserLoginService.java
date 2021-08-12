package com.albert.redis.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

/**
 * @author yangjunwei
 * @date 2021/8/11 5:06 下午
 */
@Service
public class UserLoginService {

    public static void main(String[] args) {
        int size = 10000;
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("用户登陆过但是布隆过滤器没有判断出来");
            }
        }

        int count = 0;
        //测试布隆过滤器的误判
        for (int i = size; i < size + 2000; i++) {
            if(bloomFilter.mightContain(i)){
                count++;
            }
        }

        System.out.println(count);
    }
}
