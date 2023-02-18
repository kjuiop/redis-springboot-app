package io.gig.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : JAKE
 * @date : 2023/02/18
 */
@SpringBootApplication
public class RedisSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSentinelApplication.class, args);
    }

}
