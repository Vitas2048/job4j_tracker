package ru.job4j.tracker.action;

import ru.job4j.tracker.Store;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;

public class ReplaceAction implements UserAction {

    private final Output out;

    public ReplaceAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "=== Edit item ====";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("=== Edit item ===");
        int id = input.askInt("Enter id: ");
        String name = input.askStr("Enter new name for Item: ");
        Item item = new Item(name);
        if (tracker.replace(id, item)) {
            out.println("Edit item is done.");
        } else {
            out.println(String.format("Item with id=%s not found.", id));
        }
        return true;
    }
}
