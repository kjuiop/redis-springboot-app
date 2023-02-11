package io.gig.redis;

import io.gig.redis.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : JAKE
 * @date : 2023/02/10
 */
@SpringBootApplication
@RequiredArgsConstructor
public class ChattingApplication implements CommandLineRunner {

    private final ChatService chatService;

    public static void main(String[] args) {
        SpringApplication.run(ChattingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started...");

        chatService.enterChatRoom("chat-room:1");
    }
}
