package com.albert.boot.myspringboot.webserver;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public interface WebServer {

    void start();

    void startServer(WebApplicationContext applicationContext);


}
