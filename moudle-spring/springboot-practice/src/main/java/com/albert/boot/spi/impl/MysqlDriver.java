package com.albert.boot.spi.impl;

import com.albert.boot.spi.DbDriver;

/**
 * @author yjw
 * @time 2024/6/27 20:55
 */
public class MysqlDriver implements DbDriver {
    @Override
    public void buildDriver() {
        System.out.println("MySQL驱动");
    }
}
