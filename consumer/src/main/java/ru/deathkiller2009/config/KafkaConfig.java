package ru.deathkiller2009.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.deathkiller2009.dto.WeatherCondition;
import ru.deathkiller2009.dto.WeatherReport;

import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaConsumer<String, WeatherReport> kafkaConsumer(@Value("${spring.kafka.bootstrap-servers}") String serversConfig,
                                                              @Value("${kafka.key.deserializer}") String keyDeserializer,
                                                              @Value("${kafka.value.deserializer}") String valueDeserializer) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serversConfig);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        properties.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new KafkaConsumer<>(properties);
    }

}
