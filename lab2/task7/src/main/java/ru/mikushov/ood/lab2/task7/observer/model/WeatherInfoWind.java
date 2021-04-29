package ru.mikushov.ood.lab2.task7.observer.model;

import ru.mikushov.ood.lab2.task7.observable.WindDirection;
import ru.mikushov.ood.lab2.task7.observer.model.WeatherInfo;

public class WeatherInfoWind extends WeatherInfo {
    private double windSpeed = 0;
    private WindDirection windDirection;

    public WeatherInfoWind(double temperature, double humidity, double pressure, double windSpeed, WindDirection windDirection) {
        super(temperature, humidity, pressure);
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public WeatherInfoWind(double temperature, double humidity, double pressure, double windSpeed, int windDirection) {
        super(temperature, humidity, pressure);
        this.windSpeed = windSpeed;
        this.windDirection = WindDirection.toWeatherDirection(windDirection);
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }
}
