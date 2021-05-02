package ru.mikushov.ood;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            integers.add(10);
        }

        integers.add(2, 100);
        integers.add(2, 100);
        System.out.println(integers);
    }
}