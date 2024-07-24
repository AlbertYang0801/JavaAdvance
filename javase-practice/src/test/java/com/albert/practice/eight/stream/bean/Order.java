package com.albert.practice.eight.stream.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2024/7/24
 */
@Data
@AllArgsConstructor
public class Order{
    private List<Integer> itemNameList;

}