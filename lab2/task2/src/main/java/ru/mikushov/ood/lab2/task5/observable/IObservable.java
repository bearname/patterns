package ru.mikushov.ood.lab2.task5.observable;

import ru.mikushov.ood.lab2.task5.observer.IObserver;

public interface IObservable<T> {
    void registerObserver(IObserver<T> observer);

    void notifyObservers();

    void removeObserver(IObserver<T> observer);
}
