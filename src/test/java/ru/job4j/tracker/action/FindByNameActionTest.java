package ru.job4j.tracker.action;

import org.junit.Test;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("item"));
        Item item = new Item("name");
        tracker.add(item);
        tracker.add(item);
        FindByNameAction action = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("name");

        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(item + ln + item + ln));
    }

}