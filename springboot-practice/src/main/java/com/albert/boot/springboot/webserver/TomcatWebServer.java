package com.albert.boot.springboot.webserver;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public class TomcatWebServer implements WebServer{

    @Override
    public void start() {
        System.out.println("tomcat start");
    }

}
