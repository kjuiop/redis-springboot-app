package io.gig.redis.controller;

import io.gig.redis.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : JAKE
 * @date : 2023/02/09
 */
@RestController
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @PostMapping("/rank")
    public Boolean setScore(
            @RequestParam(name = "user_id") String userId,
            @RequestParam int score
    ) {
        return rankingService.setUserScore(userId, score);
    }

    @GetMapping("/rank")
    public Long getUserRank(
            @RequestParam(name = "user_id") String userId
    ) {
        return rankingService.getUserRanking(userId);
    }

    @GetMapping("/top-rank")
    public List<String> getTopRank() {
        return rankingService.getTopRank(3);
    }
}
