package io.gig.redis.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @author : JAKE
 * @date : 2023/02/11
 */
@Service
public class ChatService implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("Message : " + message.toString());
    }
}
