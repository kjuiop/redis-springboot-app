package io.gig.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @author : JAKE
 * @date : 2023/02/06
 */
@RestController
public class LoginController {

    HashMap<String, String> sessionMap = new HashMap<>();

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        sessionMap.put(session.getId(), name);
        return "login success";
    }

    @GetMapping("/name")
    public String getName(HttpSession session) {
        String name = sessionMap.get(session.getId());
        return name;
    }
}
