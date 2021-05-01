package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Lemon extends CondimentDecorator {
    private final int quantity;

    public Lemon(Beverage beverage, int quantity) {
        super(beverage);
        this.quantity = quantity;
    }

    @Override
    public String getCondimentDescription() {
        return "Lemon x " + quantity;
    }

    @Override
    public double getCondimentCost() {
        return 10.0 * quantity;
    }
}
