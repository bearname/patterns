package ru.mikushov.ood.coffee.beverage;

public class Latte extends Coffee {
    private final CoffeePortionType portionType;

    public Latte() {
        this(CoffeePortionType.Standard);
    }

    public Latte(CoffeePortionType portionType) {
        super("Latte " + portionType);
        this.portionType = portionType;
    }

    @Override
    public double getCost() {
        if (portionType == CoffeePortionType.Standard) {
            return 90;
        }
        return 130;
    }
}