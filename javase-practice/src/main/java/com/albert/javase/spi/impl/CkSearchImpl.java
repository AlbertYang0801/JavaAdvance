package com.albert.javase.spi.impl;

import com.albert.javase.spi.ISearch;

public class CkSearchImpl implements ISearch {
    @Override
    public void search(String key) {
        System.out.println("使用ck查询");
    }

}
