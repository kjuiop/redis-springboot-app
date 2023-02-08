package io.gig.redis.service;

import io.gig.redis.dto.UserProfile;
import org.springframework.stereotype.Service;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@Service
public class UserService {


    public UserProfile getUserProfile(String userId) {
        return UserProfile.builder()
                .name("username")
                .age(3)
                .build();
    }
}
