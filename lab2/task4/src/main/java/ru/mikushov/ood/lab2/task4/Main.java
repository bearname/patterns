package ru.mikushov.ood.lab2.task4;

import ru.mikushov.ood.lab2.task4.observable.WeatherData;
import ru.mikushov.ood.lab2.task4.observable.WeatherDataType;
import ru.mikushov.ood.lab2.task4.observer.Display;
import ru.mikushov.ood.lab2.task4.observer.StatisticViewImpl;
import ru.mikushov.ood.lab2.task4.observer.StatsDisplay;

public class Main {
    public static void main(String[] args) {
        WeatherData indoorWeatherData = new WeatherData(WeatherDataType.INDOOR);
        WeatherData outdoorWeatherData = new WeatherData(WeatherDataType.INDOOR);

        Display display = new Display();
        indoorWeatherData.registerObserver(display);
        outdoorWeatherData.registerObserver(display);

        final StatisticViewImpl statisticView = new StatisticViewImpl();
        StatsDisplay statsDisplay = new StatsDisplay(statisticView);
        outdoorWeatherData.registerObserver(statsDisplay);

        indoorWeatherData.setMeasurements(28, 0.7, 760);
        outdoorWeatherData.setMeasurements(4, 0.8, 761);
        indoorWeatherData.setMeasurements(22, 0.7, 760);
    }
}