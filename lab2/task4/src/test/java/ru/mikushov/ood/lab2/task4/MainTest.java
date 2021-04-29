package ru.mikushov.ood.lab2.task4;

import org.junit.Test;
import ru.mikushov.ood.lab2.task4.observable.WeatherData;
import ru.mikushov.ood.lab2.task4.observable.WeatherDataType;
import ru.mikushov.ood.lab2.task4.observer.Display;

public class MainTest {
    @Test
    public void indoorAndOutdoor() {
        WeatherData indoorWeatherData = new WeatherData(WeatherDataType.INDOOR);
        WeatherData outdoorWeatherData = new WeatherData(WeatherDataType.OUTDOOR);

        Display display = new Display();
        indoorWeatherData.registerObserver(display);
        outdoorWeatherData.registerObserver(display);

        indoorWeatherData.setMeasurements(28, 0.7, 760);
        outdoorWeatherData.setMeasurements(4, 0.8, 761);
        indoorWeatherData.setMeasurements(22, 0.7, 760);
    }
}