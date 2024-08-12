package com.albert.es.easyes.entity;

import lombok.Data;
import org.dromara.easyes.annotation.IndexName;
import org.dromara.easyes.annotation.Settings;

/**
 * @author yangjunwei
 * @date 2024/8/12
 */
@Data
@IndexName
@Settings(shardsNum = 3)
public class Document {

    /**
     * es中的唯一id
     */
    private String id;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;



}

