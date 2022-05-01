package ru.job4j.collection;

import java.util.*;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int comp = 0;
        int i = 0;
            while (i < o1.split("/").length && i < o2.split("/").length
                    && o1.split("/")[i].compareTo(o2.split("/")[i]) == 0) {
                comp = comp + o2.split("/")[i].compareTo(o1.split("/")[i]);
                i++;
            }
            if (i != 0) {
                while (i < o1.split("/").length && i < o2.split("/").length) {
                    comp = comp + o1.split("/")[i].compareTo(o2.split("/")[i]);
                    i++;
                }
            } else {
                comp = comp + o2.compareTo(o1);
            }
        return comp;
    }

}