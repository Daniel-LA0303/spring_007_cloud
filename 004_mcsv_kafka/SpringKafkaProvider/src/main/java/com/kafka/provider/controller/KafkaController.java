package com.kafka.provider.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class KafkaController {
	
	
	private final KafkaTemplate<String, String> kafkaTemplate;

    // Inyectamos el KafkaTemplate que configuraste en KafkaProducerConfig
    public KafkaController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestParam String msg) {
        kafkaTemplate.send("test-topic", msg);
        return ResponseEntity.ok("Mensaje enviado a Kafka: " + msg);
    }

}
