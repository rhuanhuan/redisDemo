package resp;

import redis.clients.jedis.Jedis;

public class RESPTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6378);
        jedis.set("a", "a");
        /**
         * 在启动的6378 端口那边可以看到如下内容
         * *3
         * $3
         * SET
         * $1
         * a
         * $1
         * a
         */
        jedis.close();
    }
}
