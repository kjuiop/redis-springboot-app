package io.gig.redis.config;

import io.gig.redis.listener.OrderEventStreamListener;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import java.time.Duration;

/**
 * @author : JAKE
 * @date : 2023/02/26
 */
@Configuration
@RequiredArgsConstructor
public class RedisStreamConfig {

    private static final String STREAMS_ORDER_KEY = "order-events";
    private static final String PAYMENT_CONSUMER_GROUP_NAME = "payment-service-group";
    private static final String INSTANCE_NAME = "instance-1";

    private final OrderEventStreamListener orderEventStreamListener;

    /**
     * payment-consumer-group 은 미리 생성되어 있어야 한다.
     *
     * XGROUP CRAETE order-events payment-service-group $ MKSTREAM
     * MKSTREAM 옵션은 만일 이 order-events 라는 스티림이 존재하지 않으면 생성한다는 옵션
     *
     * XGROUP CRAETE order-events notification-service-group $ MKSTREAM
     * XGROUP CRAETE payment-events notification-service-group $ MKSTREAM
     *
     * @param factory
     * @return
     */
    @Bean
    public Subscription subscription(RedisConnectionFactory factory) {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .build();

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory, options);

        Subscription subscription = listenerContainer.receiveAutoAck(Consumer.from(PAYMENT_CONSUMER_GROUP_NAME, INSTANCE_NAME),
                StreamOffset.create(STREAMS_ORDER_KEY, ReadOffset.lastConsumed()), orderEventStreamListener);

        listenerContainer.start();

        return subscription;
    }

}
