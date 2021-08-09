package com.albert.redis.datastructure;

import com.albert.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 二、list 数据结构实战 - 文章推送
 *
 * @author yangjunwei
 * @date 2021/7/19 7:39 下午
 */
@Service
public class List_ArticlePushService {

    @Autowired
    RedisUtil redisUtil;

    private final String ARTICLE = "article:";

    /**
     * 添加文章
     *
     * @param publicId  公众号Id
     * @param articleId 文章
     */
    public void addArticle(String publicId, String articleId) {
        redisUtil.lLeftPush(ARTICLE + publicId, articleId);
    }

    /**
     * 获取最近推送的文章
     *
     * @param publicId 公众号Id
     * @param top      数量
     */
    public List<String> getArticleTop(String publicId, int top) {
        return redisUtil.lRange(ARTICLE + publicId, 0, top);
    }


}
