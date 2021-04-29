package ru.mikushov.ood.lab2.task7;

import ru.mikushov.ood.lab2.task7.observable.WeatherData;
import ru.mikushov.ood.lab2.task7.observer.Display;

public class Main {
    public static void main(String[] args) {
        WeatherData fisherman = new WeatherData();

        Display display = new Display();
        fisherman.registerObserver("changeTemperature", display);
        fisherman.registerObserver("changePressure", display);

        fisherman.setTemperature(-30);
    }
}