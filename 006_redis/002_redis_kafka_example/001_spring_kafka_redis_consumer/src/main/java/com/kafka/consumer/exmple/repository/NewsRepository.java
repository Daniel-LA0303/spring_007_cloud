package com.kafka.consumer.exmple.repository;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;

import reactor.core.publisher.Mono;

@Repository
public interface NewsRepository {

    Mono<Boolean> saveNews(String date, Object newsObject) throws JsonProcessingException;

}