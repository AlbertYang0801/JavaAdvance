package com.albert.redis.datastructure;

import com.albert.redis.TestApplication;
import com.albert.redis.entry.SellScore;
import com.albert.utils.jackson.JsonUtil;
import com.albert.utils.localdatetime.LocalDateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 数据结构实战 - 测试类
 *
 * @author yangjunwei
 * @date 2021/7/19 7:57 下午
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class DataStructureTest {

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

    /**
     * string - 文章点赞数量
     */
    @Test
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
    @Test
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
     * hash - 购物车
     */
    @Test
    public void hashTest() {
        String userId = "110";
        String book = "book";
        String ipone = "ipone";
        String ipad = "ipad";
        hashShopCartService.addSell(userId, book, 10);
        hashShopCartService.addSell(userId, ipone, 1);
        hashShopCartService.addSell(userId, ipad, 1);

        //购物车ipone数量加1
        hashShopCartService.addSellNum(userId, ipone, 1);

        //统计购物车商品数量
        long cellCount = hashShopCartService.countShopCartSell(userId);
        System.out.println("购物车商品数量为：" + cellCount);

        //查询购物车商品信息和数量
        Map<Object, Object> shopCartList = hashShopCartService.getShopCartList(userId);
        System.out.println("购物车信息为：" + JsonUtil.toString(shopCartList));
    }

    /**
     * set - 预约
     */
    @Test
    public void setOneTest() {
        String attractionName = "杭州西湖";
        String ming = "ming110";
        String hong = "hong110";
        String lr = "luren";

        //用户预约景点
        setReservationService.add(attractionName, ming);
        setReservationService.add(attractionName, hong);
        setReservationService.add(attractionName, lr);

        //取消预约
        setReservationService.delete(attractionName, lr);

        //获取预约总人数
        Long count = setReservationService.count(attractionName);
        System.out.println("获取用户预约总人数：" + count);

        //判断用户是否预约过
        boolean reservation = setReservationService.isReservation(attractionName, lr);
        System.out.println(lr + "用户是否预约过：" + reservation);

        //获取预约用户列表
        Set<String> userSet = setReservationService.reservationDetails(attractionName);
        System.out.println(attractionName + "预约列表：" + JsonUtil.toString(userSet));

    }

    /**
     * set - 抽奖
     */
    @Test
    public void setTwoTest() {
        String ming = "ming110";
        String hong = "hong110";
        String lr = "luren";
        //参加抽奖
        setLuckDrawService.join(ming);
        setLuckDrawService.join(hong);
        setLuckDrawService.join(lr);

        //统计参与人数
        long count = setLuckDrawService.count();
        System.out.println("参与抽奖的人数为：" + count);

        //不限次数随机抽两个人
        List<String> pump = setLuckDrawService.pump(2);
        System.out.println("安慰奖不限次数，抽两个人：" + JsonUtil.toString(pump));


        //只限抽一次，出队
        List<String> list = setLuckDrawService.pumpSingle(1);
        System.out.println("终极大奖，抽一个人，中奖人为：" + JsonUtil.toString(list));

        //出队后剩下的人
        Set<String> luckDrawList = setLuckDrawService.getLuckDraw();
        System.out.println("未中终极大奖的人：" + JsonUtil.toString(luckDrawList));
    }

    /**
     * set - 共同关注的人和可能认识的人
     */
    @Test
    public void setThreeTest() {
        String userA = "王";
        String userB = "汇";

        setPersonService.insert(userA, "小明", "王丽", "阿飞", "风哥", "李站");
        setPersonService.insert(userB, "王丽", "阿飞", "红红");

        Set<String> commonConcern = setPersonService.commonConcern(userA, userB);
        System.out.println("共同关注的人：" + JsonUtil.toString(commonConcern));

        Set<String> mayKnow = setPersonService.mayKnow(userA, userB);
        System.out.println("userB可能认识的人：" + JsonUtil.toString(mayKnow));
    }

    /**
     * zset - 商品销售排行榜
     */
    @Test
    public void zsetTest() {
        String bookId = "book110";
        String ipone = "ipone12";
        String ipad = "ipad2020";

        zsetGoodsSellSortService.addGoodsSell(bookId, 100);
        zsetGoodsSellSortService.addGoodsSell(ipone, 12);
        zsetGoodsSellSortService.addGoodsSell(ipad, 268);

        List<SellScore> sellScores = zsetGoodsSellSortService.goodsSellSortTop(5);
        System.out.println("商品销售排行版：" + JsonUtil.toString(sellScores));

        zsetGoodsSellSortService.addGoodsSell(ipone, 10000);
        System.out.println("ipone搞活动，销售量增加10000");

        List<SellScore> newSellScores = zsetGoodsSellSortService.goodsSellSortTop(5);
        System.out.println("商品销售排行版：" + JsonUtil.toString(newSellScores));
    }

    /**
     * bitmaps-用户签到
     */
    @Test
    public void bitmapsTest() {
        String userId = "xiaoming";
        //签到
        boolean signInFlag = bitmapsSignInService.signIn(userId);
        System.out.println(userId + " => 签到状态：" + signInFlag);
        String month = LocalDateTimeUtils.formatNow(LocalDateTimeUtils.YEAR_MONTH);
        int signInCount = bitmapsSignInService.countMonthSignIn(userId, month);
        System.out.println(userId + " => " + month + " => 签到次数为:" + signInCount);
    }


}
