package ru.deathkiller2009;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WeatherConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherConsumerApplication.class, args);
    }


}
