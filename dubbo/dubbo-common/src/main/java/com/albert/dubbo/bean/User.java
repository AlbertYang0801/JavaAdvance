package com.albert.dubbo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yangjunwei
 * @date 2024/7/25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -4145919990389702275L;

    private String uid;

    private String userName;


}
