package io.gig.redis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JAKE
 * @date : 2023/02/06
 */

@RestController
@RequiredArgsConstructor
public class MainController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @PostMapping("fruit")
    public String setFruit(@RequestParam String name) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set("fruit", name);

        return "saved";
    }

    @GetMapping("fruit")
    public String getFruit() {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String fruitName = ops.get("fruit");

        return fruitName;
    }
}
