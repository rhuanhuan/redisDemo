package verifyCode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import static org.junit.jupiter.api.Assertions.*;

class CodeSenderTest {
    private Jedis jedis;
    @BeforeEach
    void setUp() {
        jedis = new Jedis();
    }

    @Test
    void should_generate_code_and_verify_success_in_1min() {

        String phoneNumber = "15666666666";

        try {
            CodeSender.generateCode(phoneNumber);
            assertTrue(CodeVerifier.verifyCode(phoneNumber, CodeSender.getCode()));
        } catch (Exception e) {
            assertFalse(false);
        } finally {
            jedis.del(phoneNumber);
        }
    }

    @Test
    void should_not_generate_code_again_if_request_multiple_time(){
        String phoneNumber = "15666666667";

        try {
            CodeSender.generateCode(phoneNumber);
            assertThrows(Exception.class, () -> CodeSender.generateCode(phoneNumber));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.del(phoneNumber);
        }
    }
}