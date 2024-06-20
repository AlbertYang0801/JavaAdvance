package com.albert.cache.bloomfilter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Service;

/**
 * Guava 的布隆过滤器
 * @author yangjunwei
 * @date 2021/8/11 5:06 下午
 */
@Service
public class UserLoginService {

    public static void main(String[] args) {
        int size = 10000;
        //布隆过滤器存放数据类型，预期插入数据长度，误判率
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size,0.1);

        //插入0～10000
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        for (int i = 0; i < size; i++) {
            if (!bloomFilter.mightContain(i)) {
                System.out.println("用户登陆过但是布隆过滤器没有判断出来");
            }
        }

        int count = 0;
        //10001～13000
        for (int i = 10001; i < size + 3000; i++) {
            if(bloomFilter.mightContain(i)){
                count++;
            }
        }
        System.out.println("3000个元素发生误判的个数:"+count);
    }
}
