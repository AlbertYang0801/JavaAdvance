package com.albert.mysql.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author yangjunwei
 * @since 2024-08-06
 */
@Getter
@Setter
@TableName("t2")
@ApiModel(value = "T2Do对象", description = "")
public class T2Do implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("a")
    private Integer a;

    @TableField("b")
    private Integer b;


}
