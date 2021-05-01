package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Syrup extends CondimentDecorator {
    private final SyrupType syrupType;

    public Syrup(Beverage beverage, SyrupType syrupType) {
        super(beverage);
        this.syrupType = syrupType;
    }

    @Override
    public String getCondimentDescription() {
        return syrupType + " syrup";
    }

    @Override
    public double getCondimentCost() {
        return 15;
    }
}
