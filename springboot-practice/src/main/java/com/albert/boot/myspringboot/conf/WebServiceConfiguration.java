package com.albert.boot.myspringboot.conf;

import com.albert.boot.myspringboot.contational.MyContationalOnClass;
import com.albert.boot.myspringboot.webserver.JettyWebServer;
import com.albert.boot.myspringboot.webserver.TomcatWebServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入 web 服务器
 * 根据自定义条件注解选择
 * @author yangjunwei
 * @date 2024-05-07
 */
@Configuration
public class WebServiceConfiguration {

    /**
     * 条件选择注解
     * @return
     */
    @Bean
    @MyContationalOnClass(className = "org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }

    @Bean
    @MyContationalOnClass(className = "org.eclipse.jetty.server.jetty")
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }


}
