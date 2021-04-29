package ru.mikushov.ood.lab2.task5.observable;

import ru.mikushov.ood.lab2.task5.observer.BaseObserver;

public interface IObservable<T> {
    void registerObserver(BaseObserver<T> observer);

    void notifyObservers();

    void removeObserver(BaseObserver<T> observer);
}
