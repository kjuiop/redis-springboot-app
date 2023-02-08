package io.gig.redis.service;

import io.gig.redis.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {

        String username = null;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String cachedName = ops.get("nameKey:" + userId);
        if (StringUtils.hasText(cachedName)) {
            username = cachedName;
        } else {
            username = externalApiService.getUserName(userId);
            ops.set("nameKey:" + userId, username, 20, TimeUnit.SECONDS);
        }

        int userAge = externalApiService.getUserAge(userId);

        return UserProfile.builder()
                .name(username)
                .age(userAge)
                .build();
    }
}
