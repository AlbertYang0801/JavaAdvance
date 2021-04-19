package com.albert.spring.filtermoudle.conf;

import com.albert.filter.filter.ConfFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * 第二种过滤器配置方式：配置类
 * 配置过滤器、过滤路径、过滤器名称
 *
 * @author Albert
 * @date 2020/9/7 16:28
 */
//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new ConfFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("confFilter");
        return registrationBean;
    }


}
