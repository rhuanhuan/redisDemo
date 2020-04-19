## 练手redis的demo
需要本地有可以无密码默认访问的redis-server。 

#### helloJedis
Jedis的一些基本接口操作

#### verifyCode
使用redis简单的实现一个定时验证码

#### redisMS
redis主从demo
使用 6379, 6380, 6381三个端口。

启动集群
一主二从一哨兵
```
cd redisMS
./start-redis.sh
```
