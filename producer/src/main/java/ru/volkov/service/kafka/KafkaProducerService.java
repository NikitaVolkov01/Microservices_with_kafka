package ru.volkov.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.volkov.dto.CustomerDTO;
import ru.volkov.entity.Customer;
import ru.volkov.mapper.CustomerMapper;
import ru.volkov.repository.CustomerRepository;

import java.util.Optional;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, CustomerDTO> kafkaTemplate;
    private final CustomerRepository customerRepository;
    private static final String TOPIC = "course";

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, CustomerDTO> kafkaTemplate, CustomerRepository customerRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.customerRepository = customerRepository;
    }

    // Метод, который будет отправлять сообщения в наш топик
    public void sendCustomerDtoById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerDTO customerDTO = CustomerMapper.INSTANCE.toDto(customer);
            System.out.println(customerDTO);
            kafkaTemplate.send(TOPIC, customerDTO);
            System.out.println("в топик: " + TOPIC + "отправлено сообщение: " + customerDTO.toString());
        } else {
            // Обработка случая, если запись не найдена
            System.out.println("Customer with ID " + id + " not found.");
        }
    }
}
