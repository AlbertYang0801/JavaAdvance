package com.albert.spring.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * @author yangjunwei
 * @date 2024-06-25
 */
@Component
@Data
public class StudentService {

    @NotNull(message = "email为空")
    public String email;

    public int test() {
        return 1;
    }

    @Override
    public String toString() {
        return "student";
    }

}
