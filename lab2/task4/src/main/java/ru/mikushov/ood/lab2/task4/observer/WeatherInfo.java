package ru.mikushov.ood.lab2.task4.observer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class WeatherInfo {
    private double temperature = 0;
    private double humidity = 0;
    private double pressure = 0;
}
