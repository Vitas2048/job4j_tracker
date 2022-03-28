package ru.job4j.tracker.io;

import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        Scanner predict = new Scanner(System.in);
        System.out.println("Я великий Оракул. Что ты хочешь узнать?");
        String question = predict.nextLine();
        int answer = new Random().nextInt(3);
        switch (answer) {
            case (1) :
                System.out.println("Да");
                break;
            case (2) :
                System.out.println("Нет");
                break;
            case (3) :
                System.out.println("Возможно");
                break;
            default:
                break;
        }

    }
}
