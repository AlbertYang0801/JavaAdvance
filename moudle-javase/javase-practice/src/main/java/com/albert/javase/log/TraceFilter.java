package com.albert.javase.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 注解方式过滤器
 * @author Albert
 * @date 2020/9/7 17:23
 */
@Slf4j
@Component
@WebFilter(filterName = "annoFilter", urlPatterns = "/log/*")
public class TraceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置TraceId，log4j2.xml设置[%X{traceId}]
        TraceUtil.setTraceId();
        //过滤器放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("项目关闭，过滤器销毁");
    }


}
