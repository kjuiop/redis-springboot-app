package io.gig.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * @author : JAKE
 * @date : 2023/02/11
 */
@Service
@RequiredArgsConstructor
public class ChatService implements MessageListener {

    private final RedisMessageListenerContainer container;
    private final RedisTemplate<String, String> redisTemplate;

    /**
     * 사용자 이름을 받는 채팅방 기능
     *
     */
    public void enterChatRoom(String chatRoomName) {

        container.addMessageListener(this, new ChannelTopic(chatRoomName));

        Scanner in = new Scanner(System.in);
        while(in.hasNextLine()) {
            String line = in.nextLine();
            if (line.equals("quit")) {
                System.out.println("Quit...");
                break;
            }

            redisTemplate.convertAndSend(chatRoomName, line);
        }

        container.removeMessageListener(this);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message : " + message.toString());
    }
}
