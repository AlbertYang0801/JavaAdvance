package com.albert.spring.aop.bytebuddy.service;

/**
 * 未被Spring管理的类
 * @author admin
 * @date 2024-11-08
 */
public class PromClient {

    private String url;

    public PromClient(String url) {
        this.url = url;
    }

    public String query(String query) {
        return url + "query " + query;
    }

    public String query2(String url, String query) {
        return url + "query2 " + query;
    }

}
