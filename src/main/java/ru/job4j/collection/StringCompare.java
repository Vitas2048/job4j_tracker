package ru.job4j.collection;

import java.util.Comparator;
import java.util.List;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int result = 0;
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i < min; i++) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
        }

        return result != 0 ? result : Integer.compare(left.length(), right.length());
    }
}