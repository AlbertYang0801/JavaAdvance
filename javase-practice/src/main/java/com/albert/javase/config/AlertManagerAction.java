package com.albert.javase.config;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhangjiangyu
 * - path: /console/v1/dispatch/updateConditions.json
 *         method: POST
 *         methodName:
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlertManagerAction implements Serializable {
    private String path;
    private String method;
    private String methodName;
}
