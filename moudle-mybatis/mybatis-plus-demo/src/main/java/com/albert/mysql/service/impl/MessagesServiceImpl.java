package com.albert.mysql.service.impl;

import com.albert.mysql.entity.MessagesDo;
import com.albert.mysql.mapper.MessagesMapper;
import com.albert.mysql.service.IMessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-13
 */
@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, MessagesDo> implements IMessagesService {

}
