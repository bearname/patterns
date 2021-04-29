package ru.mikushov.ood.lab2.task2;

import ru.mikushov.ood.lab2.task5.observable.IObservable;
import ru.mikushov.ood.lab2.task5.observer.IObserver;
import ru.mikushov.ood.lab2.task5.observer.WeatherInfo;

public class SelfDeleteObserver implements IObserver<WeatherInfo> {
    private final IObservable<WeatherInfo> observable;

    public SelfDeleteObserver(IObservable<WeatherInfo> observable) {
        this.observable = observable;
    }

    @Override
    public void update(WeatherInfo data) {
        System.out.println(data.toString());
        observable.removeObserver(this);
    }
}