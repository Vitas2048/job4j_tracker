package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Attachment {
    private String name;
    private int size;

    public Attachment(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public static void attachmentSortByName(List<Attachment> attachments) {
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment a1 = (Attachment) o1;
                Attachment a2 = (Attachment) o2;
                return a1.getName().compareTo(a2.getName());
            }
        };
        attachments.sort(comparator);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "{"
                + "name='" + name + '\''
                + ", size=" + size
                + '}';
    }

    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 3", 400),
                new Attachment("image 1", 100),
                new Attachment("image 2", 200)
        );
    }
}