package com.kafka.provider.exmple.service.impl;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.kafka.provider.exmple.repository.NewsRepository;
import com.kafka.provider.exmple.service.NewsService;

import reactor.core.publisher.Mono;

import static com.kafka.provider.exmple.utils.Constants.TOPIC_NAME;

@Service
public class NewsServiceImpl implements NewsService{

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final NewsRepository newsRepository;
	
	 // Constructor manual (reemplazo de @RequiredArgsConstructor)
    public NewsServiceImpl(KafkaTemplate<String, String> kafkaTemplate, NewsRepository newsRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.newsRepository = newsRepository;
    }

    @Override
    public Mono<Void> publishToMessageBroker(String date) {
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, null, date);
        return Mono.fromFuture(kafkaTemplate.send(record))
                .doOnSuccess(result -> System.out.println("Mensaje enviado a Kafka: " + date))
                .doOnError(err -> System.err.println("Error al enviar a Kafka: " + err.getMessage()))
                .then();
    }

    @Override
    public Mono<Object> getNews(String date) {
        return newsRepository.getNews(date)
            .flatMap(news -> {
                System.out.println("Información encontrada en Redis para la fecha: " + date);
                return Mono.just(news);
            })
            .switchIfEmpty(Mono.defer(() -> {
                System.out.println("No se encontró en Redis, enviando a Kafka...");
                return publishToMessageBroker(date)
                    .then(Mono.defer(() -> waitForNewsInRedis(date)));
            }));
    }

    private Mono<Object> waitForNewsInRedis(String date) {
        return Mono.defer(() -> newsRepository.getNews(date))
            .repeatWhenEmpty(repeat -> repeat.delayElements(Duration.ofSeconds(2)).take(3)) // intenta 3 veces cada 2 segundos
            .flatMap(news -> {
                if (news == null) {
                    return Mono.error(new RuntimeException("No se encontró información después de esperar."));
                }
                return Mono.just(news);
            });
    }

	
}
