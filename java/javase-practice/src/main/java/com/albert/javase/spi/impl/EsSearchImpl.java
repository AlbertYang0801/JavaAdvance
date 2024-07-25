package com.albert.javase.spi.impl;

import com.albert.javase.spi.ISearch;

public class EsSearchImpl implements ISearch {
    @Override
    public void search(String key) {
        System.out.println("使用es查询");
    }

}
