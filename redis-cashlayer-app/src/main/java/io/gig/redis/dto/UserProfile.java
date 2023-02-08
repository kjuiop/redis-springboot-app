package io.gig.redis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author : JAKE
 * @date : 2023/02/08
 */
@Getter
@Builder
@RequiredArgsConstructor
public class UserProfile {

    @JsonProperty
    private String name;

    @JsonProperty
    private int age;

}
