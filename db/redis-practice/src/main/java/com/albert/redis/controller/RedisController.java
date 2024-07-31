package com.albert.redis.controller;

import com.albert.redis.datastructure.*;
import com.albert.utils.jackson.JsonUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangjunwei
 * @date 2023/7/28
 */
@RestController
@Slf4j
public class RedisController {

    @Autowired
    String_ArticleLikeService stringArticleLikeService;

    @Autowired
    List_ArticlePushService listArticlePushService;

    @Autowired
    Hash_ShopCartService hashShopCartService;

    @Autowired
    Set_ReservationService setReservationService;

    @Autowired
    Set_LuckDrawService setLuckDrawService;

    @Autowired
    Set_PersonService setPersonService;

    @Autowired
    Zset_GoodsSellSortService zsetGoodsSellSortService;

    @Autowired
    Bitmaps_SignInService bitmapsSignInService;

    @Autowired
    RedissonClient redissonClient;

    /**
     * string - 文章点赞数量
     */
    @GetMapping("/test1")
    public void stringTest() {
        String articleId = "ACE110";
        //初始化文章点赞数
        stringArticleLikeService.setArticleLike(articleId);
        //点赞
        stringArticleLikeService.addArticleLike(articleId);
        //点赞
        stringArticleLikeService.addArticleLike(articleId);
        long likeCount = stringArticleLikeService.countArticleLike(articleId);
        System.out.println(articleId + "文章的点赞数量为=>" + likeCount);
    }

    /**
     * list - 公众号文章推送
     */
    @GetMapping("/test2")
    public void listTest() {
        String publicId = "public2021";
        int num = 5;
        for (int i = 0; i < num; i++) {
            String articleId = "book07" + i;
            //增加文章信息
            listArticlePushService.addArticle(publicId, articleId);
        }
        List<String> articleTop = listArticlePushService.getArticleTop(publicId, 5);
        System.out.println("倒序获取公众号最近的5篇文章" + JsonUtil.toString(articleTop));
    }

    /**
     * string - 文章点赞数量
     */
    @SneakyThrows
    @GetMapping("/test3")
    public void stringTest3() {
        String articleId = "ACE110";
        //初始化文章点赞数
        stringArticleLikeService.setArticleLike(articleId);
        //点赞
        stringArticleLikeService.addArticleLike(articleId);
        //点赞
        stringArticleLikeService.addArticleLike(articleId);
        Thread.sleep(1000L);
        long likeCount = stringArticleLikeService.countArticleLike(articleId);
        System.out.println(articleId + "文章的点赞数量为=>" + likeCount);
    }



}
