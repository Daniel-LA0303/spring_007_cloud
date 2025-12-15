package com.kafka.consumer.exmple.listeners;


import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.consumer.exmple.exceptions.ExternalApiException;
import com.kafka.consumer.exmple.repository.NewsRepository;
import com.kafka.consumer.exmple.service.MediaStackService;

import reactor.core.publisher.Mono;

import static com.kafka.consumer.exmple.utils.Constants.TOPIC_NAME;
import static com.kafka.consumer.exmple.utils.Constants.MESSAGE_GROUP_NAME;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class KafkaListeners {
	
	
	private static final Logger log = LoggerFactory.getLogger(KafkaListeners.class);

    private final MediaStackService mediaStackService;
    private final NewsRepository newsRepository;

    @Autowired
    public KafkaListeners(MediaStackService mediaStackService, NewsRepository newsRepository) {
        this.mediaStackService = mediaStackService;
        this.newsRepository = newsRepository;
    }

    @KafkaListener(topics = TOPIC_NAME, groupId = MESSAGE_GROUP_NAME)
    public void listener(String date) {
        log.info("Listener received: {}", date);
        mediaStackService.sendRequest(date)
            .flatMap(response -> {
              try {
                return newsRepository.saveNews(date, response.getBody());
              } catch (JsonProcessingException e) {
                return Mono.error(new RuntimeException("Error processing JSON: ", e));
              }
            })
            .doOnNext(saved -> {
              if (saved) {
                log.info("Data successfully cached.");
              }
              else {
                log.warn("The data was not cached.");
              }
            })
            .doOnError(error -> {
              if (error instanceof ExternalApiException apiEx) {
                log.error("Failure in external API - Status: {}, Body: {}", apiEx.getStatus(), apiEx.getResponseBody());
              } else {
                log.error("Error processing Kafka message: {}", error.getMessage(), error);
              }
            })
            .subscribe();
      }

}
