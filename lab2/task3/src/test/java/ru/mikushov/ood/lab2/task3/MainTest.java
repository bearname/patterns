package ru.mikushov.ood.lab2.task3;

import org.junit.Test;
import ru.mikushov.ood.lab2.task3.observable.WeatherData;
import ru.mikushov.ood.lab2.task3.observer.Display;
import ru.mikushov.ood.lab2.task3.observer.StatisticViewImpl;
import ru.mikushov.ood.lab2.task3.observer.StatsDisplay;

public class MainTest {

    @Test
    public void selfDeleteTest() {
        WeatherData weatherData = new WeatherData();

        SelfDeleteObserver selfDeleteObserver = new SelfDeleteObserver(weatherData);
        weatherData.registerObserver(selfDeleteObserver);

        final Display display = new Display();
        weatherData.registerObserver(display);

        weatherData.setMeasurements(3, 0.7, 760);
        weatherData.setMeasurements(-10, 0.8, 761);
    }

    @Test
    public void priorityTestWhenFirstAddedObserverHaveLessPriority() {
        WeatherData weatherData = new WeatherData();

        SelfDeleteObserver selfDeleteObserver = new SelfDeleteObserver(weatherData);
        weatherData.registerObserver(selfDeleteObserver);

        final Display display = new Display(2);
        weatherData.registerObserver(display);

        final StatsDisplay statsDisplay = new StatsDisplay(new StatisticViewImpl(), 3);
        weatherData.registerObserver(statsDisplay);

        weatherData.setMeasurements(3, 0.7, 760);
    }

    @Test
    public void priorityTestWhenFirstAddedObserverHaveBiggerPriority() {
        WeatherData weatherData = new WeatherData();

        SelfDeleteObserver selfDeleteObserver = new SelfDeleteObserver(weatherData);
        weatherData.registerObserver(selfDeleteObserver);

        final Display display = new Display(3);
        weatherData.registerObserver(display);

        final StatsDisplay statsDisplay = new StatsDisplay(new StatisticViewImpl(), 2);
        weatherData.registerObserver(statsDisplay);

        weatherData.setMeasurements(3, 0.7, 760);
        weatherData.setMeasurements(-10, 0.8, 761);
    }
}