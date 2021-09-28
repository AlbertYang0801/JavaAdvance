package com.albert.eightfeatures.stream;

import com.albert.eightfeatures.TestApplication;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * 并行流的练习
 * 统计 1～100000 质数的个数
 *
 * @author yangjunwei
 * @date 2021/9/28 5:02 下午
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class ParallelStreamTest {


    @Test
    public void parallelTest() {
        long start = LocalDateTimeUtils.getNowMillis();
        //串行流
        long count = IntStream.range(1, 10000000).filter(PrimeUtil::isPrime).count();
        System.out.println("串行计算结果：" + count + ";耗时：" + (LocalDateTimeUtils.getNowMillis() - start));

        long parallelStart = LocalDateTimeUtils.getNowMillis();
        //并行流(parallel)
        long parallelCount = IntStream.range(1, 10000000).parallel().filter(PrimeUtil::isPrime).count();
        System.out.println("并行计算结果：" + parallelCount + ";耗时：" + (LocalDateTimeUtils.getNowMillis() - parallelStart));
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


class PrimeUtil {

    /**
     * 判断是否为质数
     */
    public static boolean isPrime(int num) {
        int temp = num;
        if (temp < 2) {
            return false;
        }
        for (int i = 2; Math.sqrt(temp) >= i; i++) {
            if (temp % i == 0) {
                return false;
            }
        }
        return true;
    }


}




