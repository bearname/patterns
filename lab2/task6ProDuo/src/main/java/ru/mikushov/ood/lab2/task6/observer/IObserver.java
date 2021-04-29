package ru.mikushov.ood.lab2.task6.observer;

public interface IObserver<T> {
    void update(final T data);
}
