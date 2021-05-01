package ru.mikushov.ood.coffee.condiment;

import ru.mikushov.ood.coffee.beverage.Beverage;

public class CIceCubes extends CondimentDecorator {
    private final int quantity;
    private final IceCubeType type;

    public CIceCubes(Beverage beverage, int m_quantity, IceCubeType m_type) {
        super(beverage);
        this.quantity = m_quantity;
        this.type = m_type;
    }

    @Override
    public String getCondimentDescription() {
        return IceCubeType.DRY + " ice cubes x " + quantity;
    }

    @Override
    public double getCondimentCost() {
        return (type == IceCubeType.DRY ? 10 : 5) * quantity;
    }
}
