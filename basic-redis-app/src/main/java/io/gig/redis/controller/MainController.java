package io.gig.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JAKE
 * @date : 2023/02/06
 */

@RestController
public class MainController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
