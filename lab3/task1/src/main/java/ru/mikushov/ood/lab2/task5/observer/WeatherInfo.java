package ru.mikushov.ood.lab2.task5.observer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherInfo {
    private double temperature = 0;
    private double humidity = 0;
    private double pressure = 0;
}
