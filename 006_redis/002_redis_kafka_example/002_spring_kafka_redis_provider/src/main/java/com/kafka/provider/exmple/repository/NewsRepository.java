package com.kafka.provider.exmple.repository;

import reactor.core.publisher.Mono;

public interface NewsRepository {

    Mono<Object> getNews(String date); // -> mono es reactivo

}
