package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ItemAscByNameTest {

    @Test
    public void whenSort() {
        Item a = new Item("A");
        Item b = new Item("B");
        Item c = new Item("C");
        List<Item> list =  Arrays.asList(
                a, c, b
        );
        List<Item> expected =  Arrays.asList(
                a, b, c
        );
        Collections.sort(list, new ItemAscByName());
        assertEquals(list, expected);
    }
}