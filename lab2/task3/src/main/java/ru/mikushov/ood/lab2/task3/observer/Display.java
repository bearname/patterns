package ru.mikushov.ood.lab2.task3.observer;

public class Display extends BaseObserver<WeatherInfo> {
    public Display() {
        super();
    }

    public Display(int priority) {
        super(priority);
    }

    @Override
    public void update(WeatherInfo data) {
        System.out.println("Current Temp " + data.getTemperature());
        System.out.println("Current Hum " + data.getHumidity());
        System.out.println("Current Pressure " + data.getPressure());
        System.out.println("----------------");
    }
}
