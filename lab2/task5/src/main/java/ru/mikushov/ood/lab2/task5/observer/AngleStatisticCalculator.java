package ru.mikushov.ood.lab2.task5.observer;

public class AngleStatisticCalculator implements StatisticCalculator<WindDirection> {

    private String type = "none";
    private int countAccumulator = 0;
    private double cosAccumulator = 0;
    private double sinAccumulator = 0;

    public AngleStatisticCalculator(String type) {
        this.type = type;
    }

    @Override
    public void calculate(WindDirection windDirection) {
        final int value = windDirection.getValue();
        this.cosAccumulator += Math.cos((value * Math.PI) / 180.0);
        this.sinAccumulator += Math.sin((value * Math.PI) / 180.0);
        ++countAccumulator;
    }

    @Override
    public void render() {
        double average = this.getAverageAngle();
        System.out.println("Average " + this.type + " " + normalise(average));
    }

    private double normalise(double average) {
        return average < 0 ? average + 360 : average;
    }

    private double getAverageAngle() {
        return Math.atan2(this.sinAccumulator / this.countAccumulator, this.cosAccumulator / this.countAccumulator) * 180 / Math.PI;
    }
}
