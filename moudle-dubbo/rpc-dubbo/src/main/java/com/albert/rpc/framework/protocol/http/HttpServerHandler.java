package com.albert.rpc.framework.protocol.http;

import com.albert.rpc.framework.Invocation;
import com.albert.rpc.framework.register.LocalRegister;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;

/**
 * 服务提供者-通过反射执行接口，并将结果返回给客户端
 * @author yangjunwei
 * @date 2024/7/25
 */
public class HttpServerHandler {

    /**
     * 反射执行impl，并将结果返回
     * @param request
     * @param response
     */
    public void handle(HttpServletRequest request, HttpServletResponse response) {
        try {
            //从请求中反序列化请求体
            Invocation invocation = (Invocation) new ObjectInputStream(request.getInputStream()).readObject();

            //反射执行本地方法
            String interfaceName = invocation.getInterfaceName();
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());
            Object invoke = method.invoke(implClass.newInstance(), invocation.getParams());
            //将结果序列化
            String result = JSON.toJSONString(invoke);
            //将结果写入到响应中
            IOUtils.write(result, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
