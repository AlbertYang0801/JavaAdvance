package com.albert.boot.springboot.webserver;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public class JettyWebServer implements WebServer {

    @Override
    public void start() {
        System.out.println("jetty start");
    }

}
