package com.albert.practice.eight.stream;

import com.albert.practice.eight.stream.utils.PrimeUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.stream.IntStream;

/**
 * 并行流的练习
 * 统计 1～100000 质数的个数
 *
 * @author yangjunwei
 * @date 2021/9/28 5:02 下午
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ParallelStreamTest {


    @Test
    public void parallelTest() {
        long start = System.currentTimeMillis();
        //串行流
        long count = IntStream.range(1, 10000000).filter(PrimeUtil::isPrime).count();
        System.out.println("串行计算结果：" + count + ";耗时：" + (System.currentTimeMillis() - start));

        long parallelStart = System.currentTimeMillis();
        //并行流(parallel)
        long parallelCount = IntStream.range(1, 10000000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println("并行计算结果：" + parallelCount + ";耗时：" + (System.currentTimeMillis() - parallelStart));
    }

    @Test
    public void listParallelTest(){
        List<Integer> list = Lists.newArrayList();
        list.add(10);
        list.add(15);
        list.add(30);

        //parallelStream并行计算
        double asDouble = list.parallelStream().mapToInt(value -> value).average().getAsDouble();
        System.out.println(asDouble);

    }


}







