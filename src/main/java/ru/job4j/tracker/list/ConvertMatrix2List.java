package ru.job4j.tracker.list;

import java.util.ArrayList;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        int row1 = 0;
        for (int[] row : array) {
            int cell1 = 0;
            for (int cell : row) {
                list.add(array[row1][cell1]);
                cell1++;
            }
            row1++;
        }
        return list;
    }
}