package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] vLeft = left.split(". ", 2);
        String[] vRight = right.split(". ", 2);
        return Integer.compare(Integer.parseInt(vLeft[0]), Integer.parseInt(vRight[0]));
    }
}