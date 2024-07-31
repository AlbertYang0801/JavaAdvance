# 基于 Netty 实现 RPC

## rpc-client

1. 基于注解 @RpcMethod 扫描每个接口。
2. 为扫描到的接口进行动态代理，动态代理调用 server，传入 className、methodName、paramType、param 获取结果。
3. netty 进行远程服务调用。

## rpc-server

1. 通过netty接收客户端消息。
2. 根据 className、methodName、paramType、param 反射调用获取结果。

## rpc-regcenter (注册中心)

注册中心只有两种请求。
1. 注册服务
2. 查询服务

