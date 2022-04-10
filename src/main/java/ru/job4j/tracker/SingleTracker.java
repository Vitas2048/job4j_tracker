package ru.job4j.tracker;

import ru.job4j.tracker.Tracker;

public final class SingleTracker {
    private Tracker tracker = new Tracker();
    private static SingleTracker instance = null;
    private int ids = 1;
    private int size = 0;

    public static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (int index = 0; index < size; index++) {
            if (tracker.items[index].getId() == id) {
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
            tracker.items[index] = item;
            rsl = true;
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = false;
        int index = indexOf(id);
        if (index != -1) {
            System.arraycopy(tracker.items, index + 1, tracker.items, index, size - index - 1);
            tracker.items[size - 1] = null;
            size--;
            rsl = true;
        }
        return rsl;
    }

}