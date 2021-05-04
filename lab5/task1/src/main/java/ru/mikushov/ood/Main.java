package ru.mikushov.ood;

import ru.mikushov.ood.service.DocumentImpl;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        DocumentImpl document = new DocumentImpl();
        Editor editor = new Editor(menu, document);
        editor.start();
    }
}