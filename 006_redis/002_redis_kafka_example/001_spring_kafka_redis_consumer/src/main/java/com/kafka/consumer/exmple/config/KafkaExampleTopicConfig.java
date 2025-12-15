package com.kafka.consumer.exmple.config;

import static com.kafka.consumer.exmple.utils.Constants.TOPIC_NAME;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;;

public class KafkaExampleTopicConfig {
	
    @Bean
    public NewTopic newsTopic() {
        return TopicBuilder.name(TOPIC_NAME).build();
    }

}
