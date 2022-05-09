package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.Supplier;

public class ScopeInside {
    public List<Double>diapason(int start, int end, Function<Double, Double> func) {
        List<Double> f  = new ArrayList<>();
        Double x1 = 0D;
        for (int i = start; i < end ; i++) {
            double ii = i;
            f.add(func.apply(ii));
        }
        return f;
    }

    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int num : number) {
            int lTotal = total;
            total = add(
                    () -> lTotal + num
            );
        }
        System.out.println(total);
    }

    private static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }
}