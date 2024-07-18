package com.albert.junit.filtermoudle.filter;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

/**
 * 注解方式过滤器
 * @author Albert
 * @date 2020/9/7 17:23
 */
@Slf4j
@Component
@WebFilter(filterName = "annoFilter", urlPatterns = "/*")
public class AnnoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        log.info("过滤到的请求参数为:{}", JSONUtil.toJsonStr(parameterMap));
        //过滤器放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        log.info("项目关闭，过滤器销毁");
    }


}
