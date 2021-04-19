package com.albert.leetcode.tree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Albert
 * @date 2021/3/17 下午9:40
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tree {

    private int value;
    private Tree leftNode;
    private Tree rightNode;


}
