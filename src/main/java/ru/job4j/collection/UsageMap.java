package ru.job4j.collection;

import java.util.HashMap;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("vithag97@mail.ru", "Vitaly");
        map.put("vithag98@mail.ru", "Vitaly1");
        map.put("vithag99@mail.ru", "Vitaly2");
        map.put("vithag00@mail.ru", "Vitaly3");
        for (String key : map.keySet()) {
            String value = map.get(key);
            System.out.println(value);
        }
    }
}