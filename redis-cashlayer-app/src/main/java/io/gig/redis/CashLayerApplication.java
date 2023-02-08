package io.gig.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@EnableCaching
@SpringBootApplication
public class CashLayerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashLayerApplication.class, args);
    }

}
