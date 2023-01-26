package ru.job4j.tracker.action;

import org.junit.Test;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("item"));
        Item item = new Item("item2");
        tracker.add(item);
        FindByIdAction action = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("2");

        action.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is(item + ln));
    }

}