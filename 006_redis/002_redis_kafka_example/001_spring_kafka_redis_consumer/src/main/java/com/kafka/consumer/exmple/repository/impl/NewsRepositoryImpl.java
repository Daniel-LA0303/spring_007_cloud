package com.kafka.consumer.exmple.repository.impl;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.exmple.repository.NewsRepository;

import reactor.core.publisher.Mono;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    @Autowired
    public NewsRepositoryImpl(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException {
        //Duration ttl = Duration.ofHours(1);
    	//Duration ttl = Duration.ofMinutes(5);
    	Duration ttl = Duration.ofSeconds(30);
        ObjectMapper objectMapper = new ObjectMapper();
        return redisOperations.opsForValue()
                .set(date, objectMapper.readTree(newsObject.toString()), ttl);
    }
    
    /*@Override
    public Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException {
        // De momento no guardamos en ningún lado, solo simulamos éxito
        return Mono.just(Boolean.TRUE);
    }*/
}