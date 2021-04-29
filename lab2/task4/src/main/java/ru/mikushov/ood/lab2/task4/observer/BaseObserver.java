package ru.mikushov.ood.lab2.task4.observer;

import ru.mikushov.ood.lab2.task4.observable.WeatherDataType;

public abstract class BaseObserver<T> implements IObserver<T>, Comparable<BaseObserver<T>> {
    protected int priority = 0;

    public BaseObserver() {}

    public BaseObserver(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void update(WeatherDataType type, T data) {
        if (type == WeatherDataType.INDOOR) {
            System.out.println("Indoor result");
        } else if (type == WeatherDataType.OUTDOOR) {
            System.out.println("Outdoor result");
        }

        update(data);
    }

    @Override
    public int compareTo(BaseObserver<T> o) {
        return o.priority > this.priority ? 1 : -1;
    }

    @Override
    public String toString() {
        return "Priority:" + this.priority;
    }
}
