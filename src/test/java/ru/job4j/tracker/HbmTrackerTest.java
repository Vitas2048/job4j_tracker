package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HbmTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenDelete() throws Exception {
        try (var tracker = new HbmTracker()) {
            var item = new Item("test2");
            tracker.add(item);
            var before = tracker.findAll().size();
            tracker.delete(item.getId());
            var after = tracker.findAll().size();
            assertThat(after, is(before - 1));
        }
    }

    @Test
    public void whenFindById() throws Exception {
        try (var tracker = new HbmTracker()) {
            for (Item item : tracker.findAll()) {
                System.out.println(item.getId());
                tracker.delete(item.getId());
            }
            tracker.findAll().forEach(p -> System.out.println(p.getId()));
            var item = new Item("test7");
            var expect = new ArrayList<Item>();
            expect.add(item);
            tracker.add(item);
            System.out.println(expect.size() + " expect");
            var res = tracker.findByName("test7");
            assertThat(expect.get(0).getName(), is(res.get(res.size() - 1).getName()));
        }
    }

    @Test
    public void whenReplace() throws Exception {
        try (var tracker = new HbmTracker()) {
            var item = new Item("test3");
            var item2 = new Item("test4");
            tracker.add(item);
            var id1 = tracker.findById(item.getId()).getId();
            tracker.replace(id1, item2);
            System.out.println(id1 + " id");
            tracker.findAll().forEach(p -> {
                System.out.println(p.getId());
                System.out.println(p.getName());
            });
            var ids = tracker.findByName("test4");
            var id2 = ids.get(ids.size() - 1).getId();
            assertThat(id2, is(id1));
        }
    }
}