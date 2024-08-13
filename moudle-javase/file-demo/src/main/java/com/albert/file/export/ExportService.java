package com.albert.file.export;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Java实现百万数据导出
 *
 * @author yangjunwei
 * @date 2024/8/6
 */
public class ExportService {

    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1024));

    public void export() {

    }

    private long count(){
        return 1000000;
    }



}
