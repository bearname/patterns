package ru.mikushov.ood.lab2.task5.observer;

public class Display implements IObserver<WeatherInfo> {
    @Override
    public void update(WeatherInfo data) {
        System.out.println("Current Temp " + data.getTemperature());
        System.out.println("Current Hum " + data.getHumidity());
        System.out.println("Current Pressure " + data.getPressure());
        System.out.println("----------------");
    }
}
