package io.gig.redis;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : JAKE
 * @date : 2023/02/10
 */
@SpringBootApplication
public class ChattingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChattingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started...");
    }
}
