package io.gig.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * @author : JAKE
 * @date : 2023/02/09
 */
@Service
@RequiredArgsConstructor
public class RankingService {

    private static final String LEADERBOARD_KEY = "leaderBoard";

    private final StringRedisTemplate redisTemplate;

    public boolean setUserScore(String userId, int score) {

        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        zSetOps.add(LEADERBOARD_KEY, userId, score);

        return true;
    }

}
