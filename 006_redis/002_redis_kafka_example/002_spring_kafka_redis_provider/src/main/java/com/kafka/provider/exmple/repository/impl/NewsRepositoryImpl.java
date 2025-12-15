package com.kafka.provider.exmple.repository.impl;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import com.kafka.provider.exmple.repository.NewsRepository;

import reactor.core.publisher.Mono;

@Repository
public class NewsRepositoryImpl implements NewsRepository {

    private final ReactiveRedisOperations<String, Object> redisOperations;

    //Constructor equivalente a @RequiredArgsConstructor
    public NewsRepositoryImpl(ReactiveRedisOperations<String, Object> redisOperations) {
        this.redisOperations = redisOperations;
    }

    @Override
    public Mono<Object> getNews(String date) {
        return redisOperations.opsForValue().get(date);
    }
	
    /*@Override
    public Mono<Object> getNews(String date) {
        return Mono.empty();
    }*/
}
