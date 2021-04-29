package ru.mikushov.ood.lab2.task7.observable;

import ru.mikushov.ood.lab2.task7.observer.BaseObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class ObservableBase<T> implements IObservable<T> {
    private final Map<String, ConcurrentSkipListSet<BaseObserver<T>>> observersTopic = new HashMap<>();
    private final WeatherDataType type;

    protected ObservableBase(WeatherDataType type) {
        this.type = type;
    }

    @Override
    public void registerObserver(String topic, BaseObserver<T> observer) {
        ConcurrentSkipListSet<BaseObserver<T>> baseObservers = this.observersTopic.get(topic);
        if (baseObservers == null) {
            baseObservers = new ConcurrentSkipListSet<>();
        }
        baseObservers.add(observer);
        this.observersTopic.put(topic, baseObservers);
    }

    @Override
    public void notifyObservers(String topic) {
        T data = getChangedData();
        ConcurrentSkipListSet<BaseObserver<T>> topicSet = this.observersTopic.get(topic);
        if (topicSet != null) {
            topicSet.forEach(tBaseObserver -> tBaseObserver.update(topic, data));
        }
    }

    @Override
    public void removeObserver(String topic, BaseObserver<T> observer) {
        ConcurrentSkipListSet<BaseObserver<T>> baseObservers = this.observersTopic.get(topic);
        if (baseObservers != null) {
            this.observersTopic.put(topic, baseObservers);
        }
    }

    public void measurementsChanged(String topic) {
        notifyObservers(topic);
    }

    public WeatherDataType getType() {
        return type;
    }

    protected abstract T getChangedData();
}
