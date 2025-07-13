package ru.deathkiller2009.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import ru.deathkiller2009.dto.WeatherReport;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaProducer<String, WeatherReport> kafkaProducer(@Value("${spring.kafka.bootstrap-servers}") String serversConfig,
                                                                 @Value("${kafka.key.serializer}") String keySerializer,
                                                                 @Value("${kafka.value.serializer}") String valueSerializer) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serversConfig);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);

        return new KafkaProducer<>(properties);
    }

    @Bean
    public NewTopic weatherTopic() {
        return TopicBuilder.name("weather").build();
    }

}
