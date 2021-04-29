package ru.mikushov.ood.lab2.task3;

import ru.mikushov.ood.lab2.task3.observable.WeatherData;
import ru.mikushov.ood.lab2.task3.observer.Display;
import ru.mikushov.ood.lab2.task3.observer.StatisticViewImpl;
import ru.mikushov.ood.lab2.task3.observer.StatsDisplay;

public class Main {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        Display display = new Display();
        weatherData.registerObserver(display);

        final StatisticViewImpl statisticView = new StatisticViewImpl();
        StatsDisplay statsDisplay = new StatsDisplay(statisticView);
        weatherData.registerObserver(statsDisplay);

        weatherData.setMeasurements(3, 0.7, 760);
        weatherData.setMeasurements(4, 0.8, 761);

        weatherData.removeObserver(statsDisplay);

        weatherData.setMeasurements(10, 0.8, 761);
        weatherData.setMeasurements(-10, 0.8, 761);
    }
}