package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Liquor extends CondimentDecorator {
    private final LiquorType type;

    public Liquor(Beverage beverage, LiquorType type) {
        super(beverage);
        this.type = type;
    }

    @Override
    public String getCondimentDescription() {
        return type + "Liquor";
    }

    @Override
    public double getCondimentCost() {
        return 50;
    }
}
