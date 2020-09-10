package com.albert.concurrentpractice.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Albert
 * @date 2020/7/31 17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPO {

    private String userId;
    private String name;
    private String time;

}
