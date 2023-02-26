package io.gig.redis.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : JAKE
 * @date : 2023/02/26
 */
@Component
@RequiredArgsConstructor
public class OrderEventStreamListener implements StreamListener<String, MapRecord<String, String, String>> {

    private final StringRedisTemplate redisTemplate;

    private static final String STREAMS_PAYMENT_KEY = "payment-events";
    private int paymentProcessId = 0;

    @Override
    public void onMessage(MapRecord<String, String, String> message) {
        Map map = message.getValue();

        String userId = (String) map.get("userId");
        String productId = (String) map.get("productId");
        String price = (String) map.get("price");

        // 결제 관련 로직 처리
        // ...
        String paymentIdStr = Integer.toString(paymentProcessId++);



        // 결제 완료 이벤트 발생
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("userId", userId);
        fieldMap.put("productId", productId);
        fieldMap.put("price", price);
        fieldMap.put("paymentProcessId", paymentIdStr);

        redisTemplate.opsForStream().add(STREAMS_PAYMENT_KEY, fieldMap);

        System.out.println("[Order consumed] Created payment: " + paymentIdStr);
    }
}
