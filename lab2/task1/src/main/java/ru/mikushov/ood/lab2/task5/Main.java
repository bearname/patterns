package ru.mikushov.ood.lab2.task5;

import ru.mikushov.ood.lab2.task5.observable.WeatherData;
import ru.mikushov.ood.lab2.task5.observer.Display;
import ru.mikushov.ood.lab2.task5.observer.StatisticViewImpl;
import ru.mikushov.ood.lab2.task5.observer.StatsDisplay;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Display display = new Display();
        weatherData.registerObserver(display);

        final StatisticViewImpl statisticView = new StatisticViewImpl();
        StatsDisplay statsDisplay = new StatsDisplay(statisticView);
        weatherData.registerObserver(statsDisplay);

        weatherData.SetMeasurements(3, 0.7, 760);
        weatherData.SetMeasurements(4, 0.8, 761);

        weatherData.removeObserver(statsDisplay);

        weatherData.SetMeasurements(10, 0.8, 761);
        weatherData.SetMeasurements(-10, 0.8, 761);
    }
}