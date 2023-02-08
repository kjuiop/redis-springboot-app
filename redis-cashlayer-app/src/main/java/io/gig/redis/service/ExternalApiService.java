package io.gig.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@Service
@Slf4j
public class ExternalApiService {


    public String getUserName(String userId) {

        // 외부 서비스나 DB 호출했다 가정하고, 네트워크 지연시간 추가

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("external-api exception");
        }

        log.info("Getting user name from other server .....");

        if (userId.equals("A")) {
            return "Admin";
        }

        if (userId.equals("B")) {
            return "Bob";
        }

        return "";
    }

    public int getUserAge(String userId) {

        // 외부 서비스나 DB 호출
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("external-api exception");
        }

        log.info("Getting user age from other server .....");

        if (userId.equals("A")) {
            return 28;
        }

        if (userId.equals("B")) {
            return 32;
        }


        return 0;
    }
}
