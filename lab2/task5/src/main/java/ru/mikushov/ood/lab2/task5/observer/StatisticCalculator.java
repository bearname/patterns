package ru.mikushov.ood.lab2.task5.observer;

public interface StatisticCalculator<T> {
    void calculate(T value);
    void render();
}
