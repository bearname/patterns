package ru.mikushov.ood.lab2.task5.observable;

import ru.mikushov.ood.lab2.task5.observer.WeatherInfo;
import ru.mikushov.ood.lab2.task5.observer.WindDirection;

public class WeatherData extends ObservableBase<WeatherInfo> {
    private double temperature = 0.0;
    private double humidity = 0.0;
    private double pressure = 760.0;
    private double windSpeed = 0.0;
    private int windDirection = WindDirection.NONE_DIR;

    public void setMeasurements(double temp, double humidity, double pressure, double windSpeed, int windDirection) {
        this.humidity = humidity;
        this.temperature = temp;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;

        measurementsChanged();
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }

    @Override
    protected WeatherInfo getChangedData() {
        return new WeatherInfo(getTemperature(), getHumidity(), getPressure(), getWindSpeed(), WindDirection.toWeatherDirection(getWindDirection()));
    }
}
