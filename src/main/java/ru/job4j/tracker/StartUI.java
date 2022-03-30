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
                    if (tracker.findAll().length == 0) {
                        System.out.println("Хранилище не содержит заявок");
                    }
                    break;
                case (2):
                    if (tracker.findAll().length == 0) {
                        System.out.println("Изменение заявки не удалось, т.к Хранилище не содержит заявок");
                        break;
                    }
                    System.out.println("Введите id заявки которую хотите заменить");
                    int change = Integer.parseInt(scanner.nextLine());
                    System.out.println("Введите новое название заявки: ");
                    String setName1 = input.nextLine();
                    new Item().setName(setName1);
                    Item b = new Item();
                    b.setName(setName1);
                    tracker.replace(change, b);
                    if (!tracker.replace(change, b)) {
                        System.out.println("ошибка замены");
                    }
                    break;
                case (3):
                    if (tracker.findAll().length == 0) {
                        System.out.println("Удаление заявки не удалось, т.к хранилище не содержит заявок");
                        break;
                    }
                    System.out.println("Введите id заявки которую хотите удалить");
                    int del = Integer.parseInt(scanner.nextLine());
                    tracker.delete(del);
                    if (!tracker.delete(del)) {
                        System.out.println("Заявка с таким id не найдена");
                    }
                    break;
                case (4):
                    if (tracker.findAll().length == 0) {
                        System.out.println("Найти заявку не удалось, т.к. хранилище не содержит заявок ");
                        break;
                    }
                    System.out.println("Введите id заявки которую хотите вывести на экран");
                    int showid = Integer.parseInt(scanner.nextLine());

                    if (tracker.findById(showid) == null) {
                        System.out.println("Заявка с таким id не найдена");
                    } else {
                        System.out.println(tracker.findById(showid));
                    }
                    break;
                case (5):
                    if (tracker.findAll().length == 0) {
                        System.out.println("Найти заявку не удалось, т.к. хранилище не содержит заявок ");
                        break;
                    }
                    System.out.println("Введите имя заявки которую хотите вывести на экран");
                    String showName = input.nextLine();
                    for (int i = 0; i < tracker.findByName(showName).length; i++) {
                        System.out.println(tracker.findByName(showName)[i].toString());
                    }
                    if (tracker.findByName(showName).length == 0) {
                        System.out.println("Заявка с таким именем не найдена");
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