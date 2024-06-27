package com.albert.boot.myspringboot.webserver;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public class TomcatWebServer implements WebServer {

    @Override
    public void start() {
        System.out.println("tomcat start");
    }

    @Override
    public void startServer(WebApplicationContext applicationContext) {
        startTomcat(applicationContext);
    }

    private void startTomcat(WebApplicationContext applicationContext) {
        int port = 8081;
        //创建 tomcat
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost("localhost");
        Host host = new StandardHost();
        host.setName("localhost");

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);
        service.setContainer(engine);
        service.addConnector(connector);

        //创建 distpatcherServlet
        //绑定distpatcherServlet和 Spring 容器
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);

        //绑定distpatcherServlet和 tomcat
        tomcat.addServlet(contextPath, "dispatcher", dispatcherServlet);
        context.addServletMappingDecoded("/*", "dispatcher");

        try {
            //启动 tomcat
            tomcat.start();
            System.out.println("tomcat start port:" + port);
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }


}
