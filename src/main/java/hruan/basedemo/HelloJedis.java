package hruan.basedemo;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class HelloJedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        String pong = jedis.ping();
        System.out.println("ping: " + pong);

        String testKey = "test-key";
        String testValue = "test-value";

        jedis.set(testKey, testValue);
        Set<String> keys = jedis.keys("*");
        System.out.println("keys: " + keys.toString());

        String kv = jedis.get(testKey);
        StringBuilder stringBuilder = new StringBuilder();
        System.out.println(stringBuilder.append("value for ").append(testKey ).append(": ").append(kv));

        jedis.close();
    }
}
