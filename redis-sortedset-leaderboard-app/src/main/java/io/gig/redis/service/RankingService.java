package io.gig.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public Long getUserRanking(String userId) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Long rank = zSetOps.reverseRank(LEADERBOARD_KEY, userId);

        return rank;
    }

    public List<String> getTopRank(int limit) {

        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit - 1);

        return new ArrayList<>(rangeSet);
    }
}
