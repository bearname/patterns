package ru.mikushov.ood.lab2.task7.observable;

import ru.mikushov.ood.lab2.task7.observer.model.WeatherInfo;

public class WeatherData extends ObservableBase<WeatherInfo> {
    private double temperature = 0.0;
    private double humidity = 0.0;
    private double pressure = 760.0;

    public WeatherData() {
        super(WeatherDataType.INDOOR);
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
        this.pressure = pressure;
        measurementsChanged("changePressure");
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

    @Override
    protected WeatherInfo getChangedData() {
        return new WeatherInfo(getTemperature(), getHumidity(), getPressure());
    }
}
