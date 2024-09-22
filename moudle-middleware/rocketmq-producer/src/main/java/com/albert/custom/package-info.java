package com.albert.custom;

/**
 * 本包中的类主要实现了自定义消息中间件生产者功能。
 * 可以实现RocketMQ和MySQL的无缝切换。
 *
 * 使用场景：在需要MQ能力的情况，但是不想引入RocketMQ增加系统复杂性。这种情况可以采用MySQL+定时轮询效果实现简配版的消息中间件的功能。
 *
 * 条件注解：
 * - ConditionalGlobalMessageOnRocketMq：基于RocketMQ实现
 * - ConditionalGlobalMessageOnMysql：基于MySQl实现
 * - ConditionalGlobalMessageOnClose：关闭
 *
 * 生产者模版：
 * ProducerClientTemplate：提供了消息生产者的基本功能，包含发送消息、发送延时消息的能力。
 *
 */