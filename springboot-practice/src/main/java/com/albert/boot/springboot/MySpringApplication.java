package com.albert.boot.springboot;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author yangjunwei
 * @date 2024-04-23
 */
public class MySpringApplication {

    public static void run(Class clazz) {

        //Spring容器
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(clazz);
        annotationConfigWebApplicationContext.refresh();

        //开启内置容器
        startTomcat(annotationConfigWebApplicationContext);
    }

    /**
     * 启动 tomcat
     *
     * @param applicationContext
     */
    public static void startTomcat(WebApplicationContext applicationContext) {

        //创建 tomcat
        Tomcat tomcat = new Tomcat();
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");
        Connector connector = new Connector();
        connector.setPort(8081);

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
            System.out.println("tomcat start");
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

    }


}
