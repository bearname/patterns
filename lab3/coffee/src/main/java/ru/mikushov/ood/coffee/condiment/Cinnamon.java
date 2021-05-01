package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Cinnamon extends CondimentDecorator {
    public Cinnamon(Beverage beverage) {
        super(beverage);
    }

    @Override
    public String getCondimentDescription() {
        return "Cinnamon";
    }

    @Override
    public double getCondimentCost() {
        return 20;
    }
}
