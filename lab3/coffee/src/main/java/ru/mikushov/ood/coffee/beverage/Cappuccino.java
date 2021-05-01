package ru.mikushov.ood.coffee.beverage;

public class Cappuccino extends Coffee {
    private final CoffeePortionType portionType;
    public Cappuccino() {
        this(CoffeePortionType.Standard);
    }

    public Cappuccino(CoffeePortionType portionType) {
        super("Cappuccino "+ portionType);
        this.portionType = portionType;
    }

    @Override
    public double getCost() {
        if (portionType == CoffeePortionType.Standard) {
            return 80;
        }
        return 120;
    }
}
