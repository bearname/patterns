package ru.mikushov.ood.lab2.task4.observable;

import ru.mikushov.ood.lab2.task4.observer.BaseObserver;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class ObservableBase<T> implements IObservable<T> {
    private final Set<BaseObserver<T>> priorityObservers = new ConcurrentSkipListSet<>();
    private final WeatherDataType type;

    protected ObservableBase(WeatherDataType type) {
        this.type = type;
    }

    @Override
    public void registerObserver(BaseObserver<T> observer) {
        this.priorityObservers.add(observer);
    }

    @Override
    public void notifyObservers() {
        T data = getChangedData();

        priorityObservers.forEach(tBaseObserver -> tBaseObserver.update(type, data));
    }

    @Override
    public void removeObserver(BaseObserver<T> observer) {
        priorityObservers.remove(observer);
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public WeatherDataType getType() {
        return type;
    }

    protected abstract T getChangedData();
}
