package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tracker {
    protected final List<Item> items = new ArrayList<>();
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items.add(item);
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items.get(index) : null;
    }

    public List<Item> findAll() {
        return new ArrayList<>(items);
    }

    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>(items);
        for (Item item : items) {
           if (item.getName().equals(key)) {
               rsl.add(item);
           }
        }
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;

        for (int index = 0; index < size; index++) {
            if (items.get(index).getId() == id) {
                rsl = index;
                break;
            }
        }
        return rsl;
    }

    public boolean replace(int id, Item item) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            item.setId(id);
            items.add(items.get(index));
            rsl = true;
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        int index = indexOf(id);
        List<Item> items1 = new ArrayList<>(items);
        if (index != -1) {
            items1.add(items.get(index + 1));
            size--;
            rsl = true;
        }
        return rsl;
    }
}