package ru.job4j.tracker;

import java.util.Scanner;

public class Matches {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int matches = Integer.parseInt(input.nextLine());
            turn = !turn;
            if ((matches >= 1) && (matches <= 3)) {
                count -= matches;
                if (count >= 0) {
                    System.out.println("Спичек осталось: " + count);
                } else {
                    System.out.println("Спички Закончились");
                }

            } else {
                System.out.println("Дурак, использовать такое количество нельзя");
                turn = !turn;
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}