package ru.mikushov.ood.coffee.beverage;

public class Coffee extends BeverageBase {

    public Coffee() {
        super("Coffee");
    }

    public Coffee(String description) {
        super(description);
    }

    @Override
    public double getCost() {
        return 60;
    }
}
