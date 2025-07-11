package ru.deathkiller2009;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
@EnableKafka
@EnableScheduling
public class WeatherProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherProducerApplication.class, args);
    }

    @Bean
    public List<String> cities() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("cities.txt");
        return Files.readAllLines(classPathResource.getFile().toPath());
    }

}

