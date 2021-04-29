package ru.mikushov.ood.lab2.task4.observer;

public interface IObserver<T> {
    void update(final T data);
}
