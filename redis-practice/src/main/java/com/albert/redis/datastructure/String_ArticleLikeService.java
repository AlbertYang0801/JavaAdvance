package com.albert.redis.datastructure;

import com.albert.redis.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 一、string 数据结构实战 - 文章点赞数量
 *
 * @author yangjunwei
 * @date 2021/7/19 7:51 下午
 */
@Service
public class String_ArticleLikeService {

    @Autowired
    RedisUtil redisUtil;

    private final String ARTICLE_LIKE = "ArticleLike:";

    /**
     * 初始化文章点赞数量
     * @param articleId 文章Id
     */
    public void setArticleLike(String articleId) {
        redisUtil.set(ARTICLE_LIKE + articleId, "0");
    }

    /**
     * 新增点赞
     *
     * @param articleId 文章Id
     */
    public void addArticleLike(String articleId) {
        redisUtil.incrBy(ARTICLE_LIKE + articleId, 1);
    }

    /**
     * 获取点赞总数
     *
     * @param articleId 文章Id
     */
    public long countArticleLike(String articleId) {
        return Long.parseLong(redisUtil.get(ARTICLE_LIKE + articleId));
    }


}
