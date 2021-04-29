package ru.mikushov.ood.lab2.task3.observable;

import ru.mikushov.ood.lab2.task3.observer.BaseObserver;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public abstract class ObservableBase<T> implements IObservable<T> {
    private final Set<BaseObserver<T>> priorityObservers = new ConcurrentSkipListSet<>();

    @Override
    public void registerObserver(BaseObserver<T> observer) {
        this.priorityObservers.add(observer);
    }

    @Override
    public void notifyObservers() {
        T data = getChangedData();

        priorityObservers.forEach(tBaseObserver -> tBaseObserver.update(data));
    }

    @Override
    public void removeObserver(BaseObserver<T> observer) {
        priorityObservers.remove(observer);
    }

    protected abstract T getChangedData();
}
