package verifyCode;

import redis.clients.jedis.Jedis;

/**
 * 1. 输入手机号，根据手机号生成code
 * 2. 验证码 2min 过期
 * 3. 未过期时候不允许重复输入
 */
public class CodeSender {
    public static void generateCode(String phoneNumber) {
        Jedis jedis = new Jedis();
        int expireTime = 120;

        String code = getCode();
        Long ttl = jedis.ttl(phoneNumber);
        if (ttl > 0) {
            System.out.println("key is exists, please try again after " + ttl);
            return;
        }
        jedis.setex(phoneNumber, expireTime, code);
    }

    private static String getCode() {
        return "123456";
    }
}
