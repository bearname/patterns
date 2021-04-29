package ru.mikushov.ood.lab2.task7.observable;

import ru.mikushov.ood.lab2.task7.observer.model.WeatherInfoWind;

public class WeatherDataWind extends ObservableBase<WeatherInfoWind>  {
    private double temperature = 0.0;
    private double humidity = 0.0;
    private double pressure = 760.0;
    private double windSpeed = 0.0;
    private int windDirection = WindDirection.NONE_DIR;

    public WeatherDataWind() {
        super(WeatherDataType.OUTDOOR);
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
        measurementsChanged("changeTemperature");
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
        measurementsChanged("changeHumidity");
    }

    public void setPressure(double pressure) {
        measurementsChanged("changePressure");
        this.pressure = pressure;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        measurementsChanged("changeWindSpeed");
    }

    public void setWindDirection(int windDirection) {
        this.windDirection = windDirection;
        measurementsChanged("changeWindDirection");
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
    protected WeatherInfoWind getChangedData() {
        return new WeatherInfoWind(getTemperature(), getHumidity(), getPressure(), getWindSpeed(), WindDirection.toWeatherDirection(getWindDirection()));
    }
}
