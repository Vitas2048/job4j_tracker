package ru.job4j.collection;

import java.util.Comparator;
import java.util.List;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int r = 0, min = 0;
        if (Integer.compare(left.length(), right.length()) <= 0) {
            min = left.length();
        } else {
            min = right.length();
        }
        for (int i = 0; i < min; i++) {
            if (Character.compare(left.charAt(i), right.charAt(i)) != 0) {
                r = Character.compare(left.charAt(i), right.charAt(i));
                break;
            }
            if (Integer.compare(i, right.length() - 1) == 0 && Integer.compare(left.length(), right.length()) > 0) {
                r = r + Character.compare(left.charAt(i + 1), Character.MIN_VALUE);
            } else if (Integer.compare(i, left.length() - 1) == 0 && Integer.compare(right.length(), left.length()) > 0) {
                r = r + Character.compare(Character.MIN_VALUE, right.charAt(i + 1));
            }
        }
        return r;
    }
}