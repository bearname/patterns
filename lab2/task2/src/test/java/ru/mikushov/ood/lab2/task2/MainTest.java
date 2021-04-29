package ru.mikushov.ood.lab2.task2;

import org.junit.Test;
import ru.mikushov.ood.lab2.task5.observable.WeatherData;
import ru.mikushov.ood.lab2.task5.observer.Display;

public class MainTest {

    @Test
    public void test() {
        WeatherData weatherData = new WeatherData();

        SelfDeleteObserver selfDeleteObserver = new SelfDeleteObserver(weatherData);
        weatherData.registerObserver(selfDeleteObserver);

        final Display display = new Display();
        weatherData.registerObserver(display);

        weatherData.setMeasurements(3, 0.7, 760);
        weatherData.setMeasurements(-10, 0.8, 761);
    }
}