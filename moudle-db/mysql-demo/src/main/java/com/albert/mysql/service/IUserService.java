package com.albert.mysql.service;

import com.albert.mysql.model.entity.UserDo;
import com.baomidou.mybatisplus.extension.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Albert
 * @date 2020/10/29 21:16
 */
public interface IUserService extends IService<UserDo> {

    void insertUserNoException(String name);

    void insertUserException(String name);


}
