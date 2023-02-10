package io.gig.redis;

import io.gig.redis.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : JAKE
 * @date : 2023/02/10
 */
@SpringBootTest
public class SortedSetTest {

    private static final int TEST_SCOPE = 1000000;

    @Autowired
    private RankingService rankingService;

    @Test
    void insertScore() {
        for (int i=0; i<TEST_SCOPE; i++) {
            int score = (int)(Math.random() * TEST_SCOPE);
            String user_id = "user_" + i;

            rankingService.setUserScore(user_id, score);
        }
    }

    @Test
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<TEST_SCOPE; i++) {
            int score = (int)(Math.random() * TEST_SCOPE);
            list.add(score);
        }


        Instant before = Instant.now();

        // nlogn
        Collections.sort(list);

        Duration elapse = Duration.between(before, Instant.now());
        System.out.println((elapse.getNano() / TEST_SCOPE) + " ms");
    }
}
