package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class Chocolate extends CondimentDecorator {
    private final int count;

    public Chocolate(Beverage beverage) throws Exception {
        this(beverage, 1);
    }

    public Chocolate(Beverage beverage, int count) throws Exception {
        super(beverage);
        if (count < 1 || count > 5) {
            throw new Exception("chocolate count must be in range [1, 5]");
        }

        this.count = count;
    }

    @Override
    String getCondimentDescription() {
        return "Chocolate count " + count;
    }

    @Override
    double getCondimentCost() {
        return 10 * count;
    }
}
