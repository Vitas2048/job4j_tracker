package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Attachmentsort {
    public static void main(String[] args) {
        List<Attachment> attachments = Arrays.asList(
                new Attachment("image 2", 400),
                new Attachment("image 3", 100),
                new Attachment("image 1", 200)
                );
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment a1 = (Attachment) o1;
                Attachment a2 = (Attachment) o2;
                return Integer.compare(a1.getSize(), a2.getSize());
            }
        };
        attachments.sort(comparator);
        System.out.println(attachments);
        Comparator comparator1 = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Attachment a1 = (Attachment) o1;
                Attachment a2 = (Attachment) o2;
                return a1.getName().compareTo(a2.getName());
            }
        };
        attachments.sort(comparator1);
        System.out.println(attachments);
    }
}
