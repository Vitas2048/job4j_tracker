package ru.job4j.tracker.collection;

import java.util.ArrayList;
import java.util.List;

public class ConvertList {
    public static List<Integer> convert(List<int[]> list) {
        List<Integer> rsl = new ArrayList<>();
        for (int[] p : list) {
            for (int s : p) {
                rsl.add(s);
            }
        }
        return rsl;
    }
}