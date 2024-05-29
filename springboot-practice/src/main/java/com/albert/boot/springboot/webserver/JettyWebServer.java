package com.albert.boot.springboot.webserver;

import org.springframework.web.context.WebApplicationContext;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public class JettyWebServer implements WebServer {

    @Override
    public void start() {
        System.out.println("jetty start");
    }

    @Override
    public void startServer(WebApplicationContext applicationContext) {

    }


}
