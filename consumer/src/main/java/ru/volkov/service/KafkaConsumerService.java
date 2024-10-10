package ru.volkov.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.volkov.dto.CustomerDTO;

@Slf4j
@Service
public class KafkaConsumerService {

    @Autowired
    private KafkaMessageProcessingService kafkaMessageProcessingService;

    @KafkaListener(topics = "course", groupId = "my_consumer")
    public void listen(CustomerDTO customerDTO) {
        log.info("Получено сообщение: {}", customerDTO);
        System.out.println("Hello");
        System.out.println("Получено сообщение: " + customerDTO);

        if (customerDTO != null) {
            log.info("Name: {}", customerDTO.getName());
            log.info("Last Name: {}", customerDTO.getLastName());
        } else {
            log.warn("Получено пустое сообщение!");
        }

        kafkaMessageProcessingService.processMessage(customerDTO);
    }
}
