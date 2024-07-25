# 基于 Netty 实现 RPC-client


### 实现思路
1. 基于注解 @RpcMethod 扫描每个接口
2. 为扫描到的接口进行动态代理，动态代理调用 server，传入 className、methodName、paramType、param 获取结果。
3. netty 进行远程服务调用。