package ru.mikushov.ood.coffee.beverage;

public class Tea extends BeverageBase {
    public Tea() {
        super("Tea");
    }

    public Tea(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 30;
    }
}
