package com.albert.javase.apm;

import com.albert.utils.number.NumUtil;
import org.apache.commons.lang3.math.NumberUtils;

import javax.xml.crypto.Data;
import java.math.BigDecimal;

/**
 * @author yangjunwei
 * @date 2021/9/23 2:17 下午
 */
public class HealthScoreTest {

    private Double minRespTime = 500d;
    private Double maxRespTime = 2000d;

    private Double zeroScroe = 0d;
    private Double fullScroe = 100d;


    private Double minError = 0d;
    private Double maxError = 40d;


    private Double minErrorNum = 1d;
    private Double maxErrorNum = 5d;


    /**
     * 获取关键节点分数
     */
    public Double getCruxScore(Double respTime, Double error, Double errorNum, Double pod) {
        Double respTimeScore = getScore(maxRespTime, minRespTime, respTime);
        Double errorScore = getScore(maxError, minError, error);
        Double errorNumScore = getScore(maxErrorNum, minErrorNum, errorNum);
        Double podNumScore = getScore(5d, 1d, pod);

        System.out.println("errorNumScore:" + errorNumScore);
        System.out.println("errorScore:" + errorScore);
        System.out.println("respTimeScore:" + respTimeScore);
        System.out.println("podNumScore:" + podNumScore);
        Double score = respTimeScore * 0.1 + errorScore * 0.1 + errorNumScore * 0.4
                + podNumScore * 0.3 +
                +100 * 0.1;
        return score;
    }

    private static Double getScore(Double max, Double min, Double value) {
        double score = 100.00;
        try {
            if (value == null || value == 0)
                return score;
            if (min < value && value < max)
                score = decimal(100 - ((100 / (max - min)) * (value - min)));
            else if (max <= value)
                score = 0;
        } catch (Exception e) {
        }
        return score;
    }

    public static double decimal(final double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static void main(String[] args) {
        HealthScoreTest healthScoreTest = new HealthScoreTest();
        Double cruxScore = healthScoreTest.getCruxScore(0d, 0.11d, 0d, 0d);
        System.out.println(cruxScore);
//        double floor = Math.floor(5 * 1);
//        System.out.println(floor);

    }


}
