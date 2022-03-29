package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        while (run) {
            showMenu();
            Scanner input = new Scanner(System.in);
            System.out.print("Select: ");
            int select = Integer.parseInt(scanner.nextLine());
            switch (select) {
                case (0):
                    System.out.println("Введите название заявки: ");
                    String setName = input.nextLine();
                    tracker.add(new Item()).setName(setName);
                    System.out.print("Заявка добавлена \n");
                break;
                case (1):
                    for (int i = 0; i < tracker.findAll().length; i++) {
                        System.out.println(tracker.findAll()[i].toString());
                    }
                    break;
                case (2):
                    System.out.println("Введите id заявки которую хотите заменить");
                    int change = Integer.parseInt(scanner.nextLine());
                    tracker.replace(change, new Item());
                    break;
                case (3):
                    System.out.println("Введите id заявки которую хотите удалить");
                    int del = Integer.parseInt(scanner.nextLine());
                    tracker.delete(del);
                    break;
                case (4):
                    System.out.println("Введите id заявки которую хотите вывести на экран");
                    int showid = Integer.parseInt(scanner.nextLine());
                    System.out.println(tracker.findById(showid));
                    break;
                case (5):
                    System.out.println("Введите имя заявки которую хотите вывести на экран");
                    String showName = input.nextLine();
                    for (int i = 0; i < tracker.findByName(showName).length; i++) {
                        System.out.println(tracker.findByName(showName)[i].toString());
                    }
                    break;
                case (6):
                    run = false;
                    break;
                default:
                    break;

            }
        }
    }

    private void showMenu() {
        String[] menu = {
                "Add new Item", "Show all items", "Edit item",
                "Delete item", "Find item by id", "Find items by name",
                "Exit Program"
        };
        System.out.println("Menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println(i + ". " + menu[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}