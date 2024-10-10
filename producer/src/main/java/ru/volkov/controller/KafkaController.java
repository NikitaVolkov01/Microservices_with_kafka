package ru.volkov.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.volkov.service.kafka.KafkaProducerService;

@RestController
public class KafkaController {

    private final KafkaProducerService kafkaProducerService;

    public KafkaController(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> send(@PathVariable Long id) {
        kafkaProducerService.sendCustomerDtoById(id);
        return ResponseEntity.ok("Customer sent to Kafka topic");
    }
}
