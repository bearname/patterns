package ru.mikushov.ood.coffee.beverage;

public class Milkshake extends BeverageBase {
    private final MilkshakePortionType portionType;

    public Milkshake(MilkshakePortionType portionType) {
        super("Milkshake");
        this.portionType = portionType;
    }

    @Override
    public double getCost() {
        if (portionType == MilkshakePortionType.Small) {
            return 50;
        } else if (portionType == MilkshakePortionType.Average) {
            return 60;
        }
        return 80;
    }
}
