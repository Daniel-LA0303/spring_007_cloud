package com.kafka.consumer.exmple.service;

import org.springframework.http.ResponseEntity;

import reactor.core.publisher.Mono;

public interface MediaStackService {

    Mono<ResponseEntity<String>> sendRequest(String date);

}