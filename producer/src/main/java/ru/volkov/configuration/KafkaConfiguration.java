package ru.volkov.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.volkov.dto.CustomerDTO;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    //конфигурация топика в кафке
    @Bean
    public NewTopic newTopic() {
        return new NewTopic(
                "course",
                1, //количество партиций
                (short) 1 //replication factor
        );
    }

    @Bean
    public ProducerFactory<String, CustomerDTO> producerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); // Используем JsonSerializer для CustomerDTO

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, CustomerDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
