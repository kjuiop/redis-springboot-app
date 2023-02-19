package io.gig.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : JAKE
 * @date : 2023/02/19
 */
@SpringBootTest
public class RedisClusterTest {

    @Autowired
    RedisTemplate redisTemplate;

    String dummyValue = "banana";

    @Test
    void setValues() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        for (int i=0; i<1000; i++) {
            String key = String.format("name:%d", i); // name:1
            ops.set(key, dummyValue);
        }

    }

    @Test
    void getValues() {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        for (int i=0; i<1000; i++) {
            String key = String.format("name:%d", i);
            String value = ops.get(key);

            assertEquals(value, dummyValue);
        }
    }
}
