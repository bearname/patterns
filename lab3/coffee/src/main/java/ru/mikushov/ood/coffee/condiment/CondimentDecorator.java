package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public abstract class CondimentDecorator implements Beverage {
    private final Beverage beverage;

    public CondimentDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", " + getCondimentDescription();
    }

    @Override
    public double getCost() {
        return beverage.getCost() + getCondimentCost();
    }

    abstract String getCondimentDescription();

    abstract double getCondimentCost();
}
