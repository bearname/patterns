package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class CoconutFlakes extends CondimentDecorator {
    private final int mass;

    public CoconutFlakes(Beverage beverage, int mass) {
        super(beverage);
        this.mass = mass;
    }

    @Override
    public String getCondimentDescription() {
        return "Coconut flakes " + mass + "g";
    }

    @Override
    public double getCondimentCost() {
        return 1.0 * mass;
    }
}
