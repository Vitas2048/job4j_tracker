package ru.job4j.collection;

import java.util.*;

public class Departments {

    public static List<String> fillGaps(List<String> deps) {
        List<String> rsl = new ArrayList<>();
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = "";
            for (int i = 0; i < value.split("/").length; i++) {
                String s =  value.split("/")[i];
                if (i == 0) {
                    start = s;
                    tmp.add(start);
                } else if (i < value.split("/").length - 1) {
                    start = start + "/" + s;
                    tmp.add(start);
                } else {
                    tmp.add(start + "/" + s);
                    start = "";
                }
            }
        }
        rsl.addAll(tmp);
        return rsl;
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs, new DepDescComp().reversed());
    }

    public static void sortDesc(List<String> orgs) {

        Collections.sort(orgs, new DepDescComp());
    }


}