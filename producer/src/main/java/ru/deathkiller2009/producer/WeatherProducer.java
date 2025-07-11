package ru.deathkiller2009.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.deathkiller2009.dto.WeatherCondition;
import ru.deathkiller2009.dto.WeatherReport;
import ru.deathkiller2009.logic.WeatherReportGenerator;

import java.util.concurrent.TimeUnit;

@Component
public class WeatherProducer {

    private final KafkaProducer<String, WeatherReport> kafkaProducer;

    private final WeatherReportGenerator weatherReportGenerator;

    public WeatherProducer(KafkaProducer<String, WeatherReport> kafkaProducer, WeatherReportGenerator weatherReportGenerator) {
        this.kafkaProducer = kafkaProducer;
        this.weatherReportGenerator = weatherReportGenerator;
    }

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 2)
    public void produceWeatherReports() {
        kafkaProducer.send(new ProducerRecord<>("weather", weatherReportGenerator.generateReport()));
    }

}
