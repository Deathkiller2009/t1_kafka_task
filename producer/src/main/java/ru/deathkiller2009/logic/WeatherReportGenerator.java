package ru.deathkiller2009.logic;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Component;
import ru.deathkiller2009.dto.WeatherCondition;
import ru.deathkiller2009.dto.WeatherReport;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class WeatherReportGenerator {

    private final List<String> cities;

    public WeatherReportGenerator(List<String> cities) {
        this.cities = cities;
    }

    public WeatherReport generateReport() {
        List<LocalDate> week = getWeek();
        return new WeatherReport((int) (Math.random() * 36),
                WeatherCondition.values()[(int) (Math.random() * WeatherCondition.values().length)],
                cities.get((int)(Math.random() * cities.size())),
                week.get((int) (Math.random() * 7)));
    }


    private List<LocalDate> getWeek() {
        List<LocalDate> week = new ArrayList<>();
        LocalDate now = LocalDate.now();
        int dayOfWeek = now.getDayOfWeek().getValue();
        LocalDate monday = now.minusDays(Math.abs(dayOfWeek - 1));
        week.add(monday);
        for (int i = 0; i < 6; i++) {
            monday = monday.plusDays(1);
            week.add(monday);
        }
        return week;
    }

}
