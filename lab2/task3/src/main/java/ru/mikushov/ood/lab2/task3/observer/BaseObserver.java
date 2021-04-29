package ru.mikushov.ood.lab2.task3.observer;

public abstract class BaseObserver<T> implements IObserver<T>, Comparable<BaseObserver<T>> {
    protected int priority = 0;

    public BaseObserver() {}

    public BaseObserver(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
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
