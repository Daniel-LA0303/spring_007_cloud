package com.kafka.consumer.exmple.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kafka.consumer.exmple.exceptions.ExternalApiException;
import com.kafka.consumer.exmple.service.MediaStackService;

import reactor.core.publisher.Mono;

@Service
public class MediaStackServiceImpl implements MediaStackService {

    // Logger correcto (SLF4J)
    private static final Logger log = LoggerFactory.getLogger(MediaStackServiceImpl.class);

    @Value("${mediastack.api-key}")
    private String apiKey;

    @Value("${mediastack.countries}")
    private String countries;

    @Value("${mediastack.limit}")
    private String limit;

    private final WebClient webClient;

    // Constructor manual (reemplazo de @RequiredArgsConstructor)
    public MediaStackServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Mono<ResponseEntity<String>> sendRequest(String date) {
    	
    	System.out.println("**********************************");
    	System.out.println("Consulta ENVIADA");
    	
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("access_key", apiKey)
                        .queryParam("countries", countries)
                        .queryParam("limit", limit)
                        .queryParam("date", date)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::isError, response ->
                        response.bodyToMono(String.class)
                                .flatMap(body -> {
                                    log.error("Error with external API - Status: {}, Body: {}", response.statusCode(), body);
                                    return Mono.error(new ExternalApiException((HttpStatus) response.statusCode(), body));
                                })
                )
                .toEntity(String.class);
    }
}