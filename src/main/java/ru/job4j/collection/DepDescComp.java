package ru.job4j.collection;

import java.util.*;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int k = 0;
        int rsl = 0;
        String[] s1 = o1.split("/");
        String[] s2 = o2.split("/");
        for (String ss1: s1) {
            for (String ss2 :s2) {
                if (ss1.equals(ss2)) {
                    rsl = ss2.compareTo(ss1);
                } else {
                        k = 1;
                        break;
                }
            }
            if (k == 1) {
                break;
            }
        }
        if (rsl == 0) {
         return o1.compareTo(o2);
        } else {
            return rsl;
        }
    }

}