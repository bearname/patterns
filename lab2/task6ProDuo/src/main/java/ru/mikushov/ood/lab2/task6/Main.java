package ru.mikushov.ood.lab2.task6;

import ru.mikushov.ood.lab2.task6.observable.WeatherData;
import ru.mikushov.ood.lab2.task6.observable.WeatherDataWind;
import ru.mikushov.ood.lab2.task6.observable.WeatherInfoWind;
import ru.mikushov.ood.lab2.task6.observable.WindDirection;
import ru.mikushov.ood.lab2.task6.observer.*;

public class Main {
    public static void main(String[] args) {
        WeatherData indoorWeatherData = new WeatherData();
        WeatherDataWind outdoorWeatherData = new WeatherDataWind();

        Display display = new Display();
        indoorWeatherData.registerObserver(display);
        final DisplayWind displayWind = new DisplayWind();
        outdoorWeatherData.registerObserver(displayWind);

        final StatisticView<WeatherInfo> statisticView = new StatisticViewImpl();
        StatsDisplay statsDisplay = new StatsDisplay(statisticView);
        indoorWeatherData.registerObserver(statsDisplay);

        final StatisticView<WeatherInfoWind>  statisticViewWind = new StatisticViewWindImpl();
        StatsDisplayWind windStatsDisplay = new StatsDisplayWind(statisticViewWind);
        outdoorWeatherData.registerObserver(windStatsDisplay);

        indoorWeatherData.setMeasurements(28, 0.7, 760);
        outdoorWeatherData.setMeasurements(4, 0.8, 761, 12, WindDirection.EAST_DIR);
        indoorWeatherData.setMeasurements(22, 0.7, 760);
    }
}