package ru.job4j.tracker;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ItemDescByNameTest {

    @Test
    public void WhenAbC() {
        Item a = new Item("A");
        Item b = new Item("B");
        Item c = new Item("C");
        List<Item> list =  Arrays.asList(
                a, c, b
        );
        List<Item> expected =  Arrays.asList(
                c, b, a
        );
        Collections.sort(list, new ItemDescByName());
        assertEquals(list, expected);
    }
}