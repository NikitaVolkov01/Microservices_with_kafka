package ru.volkov.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.volkov.dto.CustomerDTO;
import lombok.extern.slf4j.Slf4j;
import ru.volkov.entity.Customer;
import ru.volkov.mapper.CustomerMapper;
import ru.volkov.service.CustomerService;

@Slf4j
@Service
public class KafkaConsumerService {

    private final CustomerService customerService;

    @Autowired
    public KafkaConsumerService (CustomerService customerService) {
        this.customerService = customerService;
    }

    @KafkaListener(topics = "new_topic", groupId = "my_consumer")
    public void listen(CustomerDTO customerDTO) {
        log.info("Получено сообщение: {}", customerDTO);
        System.out.println("Получено сообщение: " + customerDTO);

        if (customerDTO != null) {
            log.info("Name: {}", customerDTO.getName());
            log.info("Last Name: {}", customerDTO.getLastName());
        } else {
            log.warn("Получено пустое сообщение!");
        }

        Customer resultCustomerAfterConsumerService = CustomerMapper.INSTANCE.toEntity(customerDTO);
        customerService.saveCustomer(resultCustomerAfterConsumerService);
        System.out.println("Измененный объект сохранен в БД");
    }
}
