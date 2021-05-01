package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class ChocolateCrumbs extends CondimentDecorator {
    private final int mass;

    public ChocolateCrumbs(Beverage beverage, int mass) {
        super(beverage);
        this.mass = mass;
    }

    @Override
    public String getCondimentDescription() {
        return "Chocolate crumbs " + mass + "g";
    }

    @Override
    public double getCondimentCost() {
        return 2.0 * mass;
    }
}
