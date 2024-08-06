package com.albert.rpc.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * RPC接口调用定义
 *
 * @author yangjunwei
 * @date 2024/7/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invocation implements Serializable {


    private static final long serialVersionUID = 2376201613927402490L;

    /**
     * RPC接口名
     */
    private String interfaceName;

    /**
     * RPC方法名
     */
    private String methodName;

    /**
     * 方法入参
     */
    private Object[] params;

    /**
     * 方法参数类型
     */
    private Class[] paramTypes;


}
