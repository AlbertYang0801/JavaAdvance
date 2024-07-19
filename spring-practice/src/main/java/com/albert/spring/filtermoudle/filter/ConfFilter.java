package com.albert.spring.filtermoudle.filter;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * 第二种过滤器配置方式：使用配置类
 * @author Albert
 * @date 2020/9/7 16:24
 */
@Slf4j
public class ConfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        log.info("请求属性名称列表:{}", JSONUtil.toJsonStr(parameterMap));
        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            log.info("遍历参数名称:{}",parameterNames.nextElement());
        }
        //过滤器放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("项目关闭，过滤器销毁");
    }

}
