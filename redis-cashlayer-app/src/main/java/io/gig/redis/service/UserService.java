package io.gig.redis.service;

import io.gig.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    public UserProfile getUserProfile(String userId) {
        String username = externalApiService.getUserName(userId);
        int userAge = externalApiService.getUserAge(userId);

        return UserProfile.builder()
                .name(username)
                .age(userAge)
                .build();
    }
}
