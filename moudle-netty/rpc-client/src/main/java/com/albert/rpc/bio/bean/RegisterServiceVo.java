package com.albert.rpc.bio.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yjw
 * @date 2024/5/29 20:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterServiceVo implements Serializable {

    private String hostIp;

    private int port;

}
