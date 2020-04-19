package verifyCode;

import redis.clients.jedis.Jedis;

/**
 *
 */
public class CodeVerifier {
    public static boolean verifyCode(String phoneNumber, String code) {
        Jedis jedis = new Jedis();
        String value = jedis.get(phoneNumber);
        if (code == null) {
            return false;
        }

        return value != null & code.equals(value);
    }
}
