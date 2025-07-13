package ru.deathkiller2009.consumer;

import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.deathkiller2009.dto.WeatherReport;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class WeatherConsumer {

    private final KafkaConsumer<String, WeatherReport> kafkaConsumer;

    @PostConstruct
    public void init(){
        kafkaConsumer.assign(List.of(new TopicPartition("weather", 0)));
    }

    public WeatherConsumer(KafkaConsumer<String, WeatherReport> kafkaConsumer) {
        this.kafkaConsumer = kafkaConsumer;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Async
    public void consumeWeather() {
        while (true) {
            ConsumerRecords<String, WeatherReport> polled =
                    kafkaConsumer.poll(Duration.of(1, ChronoUnit.SECONDS));
            for (ConsumerRecord<String, WeatherReport> stringWeatherConditionConsumerRecord : polled) {
                System.out.println(stringWeatherConditionConsumerRecord.value());
            }
        }
    }
}
