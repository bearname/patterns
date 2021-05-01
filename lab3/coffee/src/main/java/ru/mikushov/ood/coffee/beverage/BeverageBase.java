package ru.mikushov.ood.coffee.beverage;

public abstract class BeverageBase implements Beverage {
    private final String description;

    protected BeverageBase(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
