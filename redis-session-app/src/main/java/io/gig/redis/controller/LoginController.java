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

    /*
     * application.yml 에 세션 설정하면 자동적으로 Redis 에 저장됨
     * spring:
     *  session:
     *   store-type: redis
     */

    @PostMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        /*
         * 1. 로컬 메모리에 세션 값을 저장하는 방식
         * sessionMap.put(session.getId(), name);
         */

        /*
         * 2. 세션에 값을 저장하는 방식
         * session.setAttribute("name", name);
         */
        session.setAttribute("name", name);

        return "login success";
    }

    @GetMapping("/name")
    public String getName(HttpSession session) {
        /*
         * 1. 로컬 메모리에 세션 값을 저장하는 방식
         * String name = sessionMap.get(session.getId());
         */

        /*
         * 2. 세션에 값을 저장하는 방식
         * String name = (String) session.getAttribute("name");
         */
        String name = (String) session.getAttribute("name");
        return name;
    }
}
