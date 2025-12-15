 package com.kafka.provider.exmple.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import com.kafka.provider.exmple.model.response.DataResponse;
import com.kafka.provider.exmple.service.NewsService;


import static com.kafka.provider.exmple.utils.Constants.DATA_FOUND_MESSAGE;
import static com.kafka.provider.exmple.utils.Constants.DATA_NOT_FOUND_MESSAGE;
import static com.kafka.provider.exmple.utils.Constants.DATE_FORMAT;
import static com.kafka.provider.exmple.utils.Constants.DATE_NOT_BLANK_MESSAGE;
import static com.kafka.provider.exmple.utils.Constants.DATE_PATTERN_MESSAGE;


@RestController
@RequestMapping(value = "/news")
public class NewsController {

    private final NewsService service;

    // Constructor manual (reemplazo de @RequiredArgsConstructor)
    public NewsController(NewsService service) {
        this.service = service;
    }

    @GetMapping
    public Mono<ResponseEntity<DataResponse<Object>>> getNews(
            @NotBlank(message = DATE_NOT_BLANK_MESSAGE)
            @Pattern(regexp = DATE_FORMAT, message = DATE_PATTERN_MESSAGE)
            @RequestParam(name = "date") String date) {

    	return service.getNews(date)
    	        .map(data -> ResponseEntity.status(HttpStatus.OK)
    	                .body(new DataResponse<>(
    	                        DATA_FOUND_MESSAGE,  
    	                        Boolean.TRUE,         
    	                        data                  
    	                )))
    	        .switchIfEmpty(Mono.just(
    	                ResponseEntity.status(HttpStatus.NOT_FOUND)
    	                        .body(new DataResponse<>(
    	                                DATA_NOT_FOUND_MESSAGE, 
    	                                Boolean.FALSE,          
    	                                null                    
    	                        ))
    	        ));


    }
}