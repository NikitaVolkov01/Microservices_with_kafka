package ru.volkov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.volkov.dto.CustomerDTO;

@Service
public class KafkaMessageProcessingService {

    @Autowired
    private KafkaTemplate<String, CustomerDTO> kafkaTemplate;

    public void processMessage(CustomerDTO customerDTO) {
        if (customerDTO != null) {
            CustomerDTO modifiedCustomerDTO = new CustomerDTO();
            modifiedCustomerDTO.setName(customerDTO.getName() + " (modified)");
            modifiedCustomerDTO.setLastName(customerDTO.getLastName());

            kafkaTemplate.send("new_topic", modifiedCustomerDTO);
            System.out.println("Сообщение отправлено в новый топик: " + modifiedCustomerDTO);
        } else {
            System.out.println("Нет сообщения для обработки!");
        }
    }
}
