package ru.deathkiller2009.dto;

import java.time.LocalDate;

public record WeatherReport(int temperature, WeatherCondition weatherCondition, String city, LocalDate localDate) {
}
