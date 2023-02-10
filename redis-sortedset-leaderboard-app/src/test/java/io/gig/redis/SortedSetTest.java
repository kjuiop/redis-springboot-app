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

    @Autowired
    private RankingService rankingService;

    @Test
    void inMemorySortPerformance() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<1000000; i++) {
            int score = (int)(Math.random() * 1000000);
            list.add(score);
        }


        Instant before = Instant.now();

        // nlogn
        Collections.sort(list);

        Duration elapse = Duration.between(before, Instant.now());
        System.out.println((elapse.getNano() / 1000000) + " ms");
    }
}
