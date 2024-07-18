package com.cryptomarket.cryptoexchange.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topicForPrice() {
        return TopicBuilder
                .name("cryptocurrency-prices")
                .partitions(5)
                .build();
    }
}
