package com.albert.study.cookie;

import com.albert.study.utils.cookie.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 测试添加cookie和读取cookie
 * @author Albert
 * @date 2020/9/4 16:07
 */
@Slf4j
@RestController
@RequestMapping("/cookie")
public class CookieController {

    @GetMapping("/add")
    public String setCookie(HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("loginUserName","测试");
        httpServletResponse.addCookie(cookie);
        return "添加成功";
    }

    @GetMapping("/read")
    public String readCookie(HttpServletRequest httpServletRequest){
        Cookie[] cookies = httpServletRequest.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("loginUserName")){
                System.out.println(cookie.getValue());
            }
        }
        //使用工具类读取cookie
        Map<String, String> map = CookieUtil.readCookie(httpServletRequest, "loginUserName");
        System.out.println(map.get("loginUserName"));
        return "读取成功";
    }


}
