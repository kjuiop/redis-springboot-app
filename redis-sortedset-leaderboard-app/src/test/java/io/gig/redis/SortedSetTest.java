package io.gig.redis;

import io.gig.redis.service.RankingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    void getRanks() {
        // 최초 네트워크 비용을 제거하기 위한 소스
        rankingService.getTopRank(1);

        // 8ms
        Instant before = Instant.now();
        Long userRank = rankingService.getUserRanking("user_100");
        Duration elapse = Duration.between(before, Instant.now());
        System.out.println(String.format("Rank(%d) - rank %d ms ", userRank, (elapse.getNano() / TEST_SCOPE)));


        // 2ms
        before = Instant.now();
        List<String> topRanker = rankingService.getTopRank(10);
        elapse = Duration.between(before, Instant.now());
        System.out.println(String.format("Range - Took %d ms ", (elapse.getNano() / TEST_SCOPE)));
    }


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
