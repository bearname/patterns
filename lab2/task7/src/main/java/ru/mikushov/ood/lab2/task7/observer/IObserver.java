package ru.mikushov.ood.lab2.task7.observer;

public interface IObserver<T> {
    void update(String topic, final T data);
}
