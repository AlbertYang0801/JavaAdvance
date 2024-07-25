package com.albert.spring.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yangjunwei
 * @date 2024-06-25
 */
@Data
public class User {
    @NotNull(message = "name为空")
    private String name;
    @NotNull(message = "email为空")
    private String email;
    @Pattern(regexp = "yyyy-MM-dd HH:mm:ss")
    private String time;


}
