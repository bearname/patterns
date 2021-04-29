package ru.mikushov.ood.lab2.task3;

import ru.mikushov.ood.lab2.task3.observable.ObservableBase;
import ru.mikushov.ood.lab2.task3.observer.BaseObserver;
import ru.mikushov.ood.lab2.task3.observer.WeatherInfo;

public class SelfDeleteObserver extends BaseObserver<WeatherInfo> {
    private final ObservableBase<WeatherInfo> observable;

    public SelfDeleteObserver(ObservableBase<WeatherInfo> observable) {
        super();
        this.observable = observable;
    }

    public SelfDeleteObserver(ObservableBase<WeatherInfo> observable, int priority) {
        super(priority);
        this.observable = observable;
    }

    @Override
    public void update(WeatherInfo data) {
        observable.removeObserver(this);
    }
}
