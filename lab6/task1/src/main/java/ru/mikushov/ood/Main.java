package ru.mikushov.ood;

import ru.mikushov.ood.app.Application;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print ("Should we use new API (y)? ");

        final Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        final Application application = new Application();
        if (userInput.equals("y") || userInput.equals("Y")) {
            application.paintPictureOnModernGraphicsRender();
        } else {
            application.paintPictureOnCanvas();
        }
    }
}