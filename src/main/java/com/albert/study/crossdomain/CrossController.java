package com.albert.study.crossdomain;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

/**
 * 解决跨域的方式：使用注解
 *
 * 只需要在Controller加上@CrossOrigin即可对整个Controller进行跨域处理
 * @author Albert
 * @date 2020/9/7 10:17
 */
@CrossOrigin
@RestController
public class CrossController {
}
