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

public class DeleteActionTest {
    @Test
    public void execute() {
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        tracker.add(new Item("item"));
        DeleteAction deleteAction = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("1");

        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString(), is("Item is successfully deleted!" + ln));
        assertThat(tracker.findAll().isEmpty(), is(true));
    }

}