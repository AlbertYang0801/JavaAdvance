package com.albert.redis.entry;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangjunwei
 * @date 2021/7/19 7:35 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellScore {

    private String sellValue;
    private Double score;

}
