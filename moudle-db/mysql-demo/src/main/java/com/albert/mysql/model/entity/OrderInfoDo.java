package com.albert.mysql.model.entity;

import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author yjw
 * @date 2024/4/12 0:40
 */
@Data
@TableName(value = "order_info")
public class OrderInfoDo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String orderName;

    private String account;

    public static OrderInfoDo build(){
        OrderInfoDo orderInfoDo = new OrderInfoDo();
        orderInfoDo.setOrderName(UUID.fastUUID().toString());
        orderInfoDo.setAccount("1000");
        return orderInfoDo;
    }


}
