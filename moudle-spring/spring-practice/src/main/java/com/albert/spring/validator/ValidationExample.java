package com.albert.spring.validator;

import cn.hutool.core.date.DateUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

/**
 * @author yangjunwei
 * @date 2024-06-25
 */
public class ValidationExample {

    public static void main(String[] args) {
        User user = new User();

        Date date = new Date();
        user.setTime(DateUtil.formatTime(date));
        System.out.println(DateUtil.formatDateTime(date));

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        System.out.println(violations.size());
        for (ConstraintViolation<User> violation : violations) {
            System.out.println(violation.getMessage());
            System.out.println(violation.getPropertyPath().toString());
        }
    }

}
