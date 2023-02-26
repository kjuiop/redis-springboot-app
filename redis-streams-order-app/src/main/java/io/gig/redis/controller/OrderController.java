package io.gig.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : JAKE
 * @date : 2023/02/26
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private static final String STREAMS_ORDER_KEY = "order-events";

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/order")
    public String getOrder(@RequestParam String userId,
                           @RequestParam String productId,
                           @RequestParam String price) {

        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("userId", userId);
        fieldMap.put("productId", productId);
        fieldMap.put("price", price);

        redisTemplate.opsForStream().add(STREAMS_ORDER_KEY, fieldMap);

        System.out.println("Order created");

        return "ok";
    }


}
