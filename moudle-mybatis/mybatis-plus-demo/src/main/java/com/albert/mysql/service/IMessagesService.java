package com.albert.mysql.service;

import com.albert.mysql.entity.MessagesDo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-13
 */
public interface IMessagesService extends IService<MessagesDo> {

    /**
     * for update 阻塞
     * @param id
     * @return
     */
    boolean lockQuery(Long id);

    boolean lockQuery2(Long id);

    /**
     * for update lock skip 非阻塞
     * @param id
     * @return
     */
    boolean lockQueryNoBlock(Long id);

    boolean lockQueryNoBlock2(Long id);

    /**
     * select+update
     * @param id
     * @return
     */
    boolean lockMsg(Long id);

    boolean lockMsg2(Long id);


}
