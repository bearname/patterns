package ru.mikushov.ood.lab2.task5.observer;

import java.util.HashMap;
import java.util.Map;

public enum WindDirection {
    NONE(-1),
    NORTH(0),
    EAST(90),
    SOUTH(180),
    WEST(270);

    public static final int NORTH_DIR = 0;
    public static final int EAST_DIR = 0;
    public static final int SOUTH_DIR = 0;
    public static final int WEST_DIR = 0;
    public static final int NONE_DIR = 0;

    private final int value;

    WindDirection(int value) {
        this.value = value;
    }

    public static WindDirection toWeatherDirection(int value) {
        Map<Integer, WindDirection> observableTypeMap = new HashMap<>();

        observableTypeMap.put(NORTH.value, NORTH);
        observableTypeMap.put(EAST.value, EAST);
        observableTypeMap.put(SOUTH.value, SOUTH);
        observableTypeMap.put(WEST.value, WEST);
        observableTypeMap.put(NONE.value, NONE);

        return observableTypeMap.get(value);
    }

    public int getValue() {
        return value;
    }
}
