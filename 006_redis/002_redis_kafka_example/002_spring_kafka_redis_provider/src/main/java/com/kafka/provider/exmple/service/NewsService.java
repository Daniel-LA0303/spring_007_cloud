package com.kafka.provider.exmple.service;

import reactor.core.publisher.Mono;

public interface NewsService {
	
    Mono<Void> publishToMessageBroker(String date);
    
    Mono<Object> getNews(String date);
    
}
