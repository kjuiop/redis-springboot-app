package io.gig.redis.listener;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author : JAKE
 * @date : 2023/02/26
 */
@Component
public class OrderEventStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        Map map = message.getValue();
        System.out.println("Map : " + map.toString());
    }
}
