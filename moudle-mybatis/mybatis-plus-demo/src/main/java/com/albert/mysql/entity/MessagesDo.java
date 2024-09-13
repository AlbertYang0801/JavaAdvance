package com.albert.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yangjunwei
 * @since 2024-09-13
 */
@Getter
@Setter
@TableName("messages")
public class MessagesDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("topic")
    private String topic;

    @TableField("status")
    private String status;

    @TableField("payload")
    private String payload;

    @TableField("enable_consume_time")
    private Long enableConsumeTime;

    @TableField("msg_id")
    private String msgId;

    @TableField("retry_count")
    private Integer retryCount;


}
