package ru.mikushov.ood.lab2.task6.observer;

import ru.mikushov.ood.lab2.task6.observable.WeatherInfoWind;

public class DisplayWind extends BaseObserver<WeatherInfoWind> {
    public DisplayWind() {
        super();
    }

    public DisplayWind(int priority) {
        super(priority);
    }

    @Override
    public void update(WeatherInfoWind data) {
        System.out.println("Current Temp " + data.getTemperature());
        System.out.println("Current Hum " + data.getHumidity());
        System.out.println("Current Pressure " + data.getPressure());
        System.out.println("Current Weather Speed " + data.getWindSpeed());
        System.out.println("Current Weather Direction " + data.getWindDirection());
        System.out.println("----------------");
    }
}
