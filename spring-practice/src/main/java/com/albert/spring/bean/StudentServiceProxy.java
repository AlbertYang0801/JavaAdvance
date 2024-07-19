package com.albert.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author yangjunwei
 * @date 2024-06-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentServiceProxy extends StudentService{

    @NotNull(message = "email为空")
    public String email;

    @Override
    public int test() {
        return 2;
    }

    @Override
    public String toString() {
        return "proxy";
    }

}
