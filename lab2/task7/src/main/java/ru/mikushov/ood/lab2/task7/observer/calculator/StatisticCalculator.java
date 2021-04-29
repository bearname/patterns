package ru.mikushov.ood.lab2.task7.observer.calculator;

public interface StatisticCalculator<T> {
    void calculate(T value);
    void render();
}
