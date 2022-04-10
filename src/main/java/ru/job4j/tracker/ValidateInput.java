package ru.job4j.tracker;

public class ValidateInput implements Input {
    private final Output out;
    private final Input in;

    public ValidateInput(Output out, Input input) {
        this.out = out;
        this.in = input;
    }

    @Override
    public String askStr(String question) {
        return in.askStr(question);
    }

    @Override
    public int askInt(String question)  {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                String rsl = in.askStr(question);
                value = Integer.parseInt(rsl);
                invalid = false;
            } catch (NumberFormatException e) {
                out.println("please enter validate data again");
            }
        } while (invalid);
        return value;
    }
}