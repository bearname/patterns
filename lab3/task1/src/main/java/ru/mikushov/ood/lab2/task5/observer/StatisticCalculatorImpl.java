package ru.mikushov.ood.lab2.task5.observer;

public class StatisticCalculatorImpl implements StatisticCalculator {
    private double minValue = Double.MAX_VALUE;
    private double maxValue = Double.MIN_VALUE;
    private double accumulator = 0;
    private int countAccumulator = 0;
    private final String type;

    public StatisticCalculatorImpl(String type) {
        this.type = type;
    }

    @Override
    public void calculate(double value) {
        if (minValue > value) {
            minValue = value;
        }
        if (maxValue < value) {
            maxValue = value;
        }
        accumulator += value;
        ++countAccumulator;

    }

    @Override
    public void render() {
        System.out.println("Max " + type + " " + maxValue);
        System.out.println("Min " + type + " " + minValue);
        System.out.println("Average " + type + " " + (accumulator / countAccumulator));
        System.out.println("----------------");
    }
}
