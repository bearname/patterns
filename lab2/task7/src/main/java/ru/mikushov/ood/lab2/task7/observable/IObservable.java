package ru.mikushov.ood.lab2.task7.observable;

import ru.mikushov.ood.lab2.task7.observer.BaseObserver;

public interface IObservable<T> {
    void registerObserver(String topic, BaseObserver<T> observer);

    void notifyObservers(String topic);

    void removeObserver(String topic, BaseObserver<T> observer);
}
