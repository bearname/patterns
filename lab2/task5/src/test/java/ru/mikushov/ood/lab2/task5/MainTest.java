package ru.mikushov.ood.lab2.task5;

import org.junit.Test;
import ru.mikushov.ood.lab2.task5.observable.WeatherData;
import ru.mikushov.ood.lab2.task5.observer.Display;
import ru.mikushov.ood.lab2.task5.observer.StatisticViewImpl;
import ru.mikushov.ood.lab2.task5.observer.StatsDisplay;
import ru.mikushov.ood.lab2.task5.observer.WindDirection;

public class MainTest {
    @Test
    public void selfDeleteTest() {
        WeatherData weatherData = new WeatherData();

        final Display display = new Display();
        weatherData.registerObserver(display);

        weatherData.setMeasurements(3, 0.7, 760, 10, WindDirection.EAST_DIR);
        weatherData.setMeasurements(-10, 0.8, 761, 1, WindDirection.NORTH_DIR);
    }

    @Test
    public void priorityTestWhenFirstAddedObserverHaveLessPriority() {
        WeatherData weatherData = new WeatherData();

        final Display display = new Display(2);
        weatherData.registerObserver(display);

        final StatsDisplay statsDisplay = new StatsDisplay(new StatisticViewImpl(), 3);
        weatherData.registerObserver(statsDisplay);

        weatherData.setMeasurements(3, 0.7, 760,10, WindDirection.EAST_DIR);
        weatherData.setMeasurements(-10, 0.8, 76, 1, WindDirection.WEST_DIR);
    }

    @Test
    public void priorityTestWhenFirstAddedObserverHaveBiggerPriority() {
        WeatherData weatherData = new WeatherData();

        final Display display = new Display(3);
        weatherData.registerObserver(display);

        final StatsDisplay statsDisplay = new StatsDisplay(new StatisticViewImpl(), 2);
        weatherData.registerObserver(statsDisplay);

        weatherData.setMeasurements(3, 0.7, 760,10, 0);
        weatherData.setMeasurements(-10, 0.8, 760, 1, 270);
    }
}