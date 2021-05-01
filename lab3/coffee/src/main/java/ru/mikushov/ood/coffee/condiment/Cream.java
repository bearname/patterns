package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Cream extends CondimentDecorator {
    public Cream(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getCondimentDescription() {
        return "Cream";
    }

    @Override
    public double getCondimentCost() {
        return 25;
    }
}
