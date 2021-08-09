# Redis 实战练习

## 八大数据结构实战 

[测试类地址](src/test/java/com/albert/redis/datastructure/DataStructureTest.java)

### 字符串 - string

1. 点赞数量

   - 新增点赞

     `incr read`

   - 点赞总数

     `get read`

2. 文章阅读量

### 列表 - list

**公众号文章订阅**

- 新增文章

  `lpush code java css`

- 获取推送的最近5篇文章

  `lrange code 0 4`

### 哈希表 - hash

**购物车**

- 添加商品

  `hset shopcart 10001 1`

- 增加商品数量

  `hincreby shopcart 10001 1`

- 统计购物车商品总数

  `hlen shopcar`

- 删除商品

  `hdel shopcart 10001`

- 获取购物车列表

  `hgetall shopcart`

### 无序集合 - set

1. 景点预约功能

   - 新增预约
   - 取消预约
   - 判断用户是否预约过
   - 统计预约总人数
   - 展示预约用户信息

2. 抽奖功能

   - 参加抽奖

   - 统计参加人数

   - 随机抽两个人（不限次数）

     `srandmember key [count]`

   - 随机抽两人（只能中一次）

     `spop key [count]`

3. 微博共同关注的人

   - 对两个用户关注的人取交集

     `sinter userA userB`

4. QQ - 可能认识的人

   - 对两个用户取差集

     `sdiff userA userB`

### 有序集合 - zset

1. 销售排行榜

   - 增加商品销量

     `zincrby book 1 java`

   - 查询前十条销售量的商品

     `zrevrange book 0 9 WITHSCORES`

2. 微博热搜排行榜

   - 添加新闻初始热度

     `zadd weibo 250000 test`

   - 点击增加热度

     `zincrby weibo 1 test`

   - 查询前十条热度最高的新闻

     `zrevrange weibo 0 9 WITHSCORES`

### 位图 - bitmaps

**用户每月签到**

- 用户签到

  `setbit signIn:YYYYMM:userId day 1`

- 统计当月签到总数

  `bitcount signIn:YYYYMM:userId`