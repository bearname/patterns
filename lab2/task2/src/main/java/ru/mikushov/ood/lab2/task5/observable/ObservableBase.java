package ru.mikushov.ood.lab2.task5.observable;

import ru.mikushov.ood.lab2.task5.observer.IObserver;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class ObservableBase<T> implements IObservable<T> {
    protected final Set<IObserver<T>> observers = ConcurrentHashMap.newKeySet();

    @Override
    public void registerObserver(IObserver<T> observer) {
        this.observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        T data = getChangedData();

        for (IObserver<T> observer : observers) {
            observer.update(data);
        }
    }

    @Override
    public void removeObserver(IObserver<T> observer) {
        observers.remove(observer);
    }

    protected abstract T getChangedData();
}
