package ru.job4j.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ScopeInsideTest {

    @Test
    public void whenLinearFunctionThenLinearResults() {
        ScopeInside function = new ScopeInside();
        List<Double> result = function.diapason(5, 8, x -> 2 * x + 1);
        List<Double> expected = Arrays.asList(11D, 13D, 15D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenQuadraticFunctionThenLinearResults() {
        ScopeInside function = new ScopeInside();
        List<Double> result = function.diapason(5, 8, x -> 5 * x * x - 10 * x);
        List<Double> expected = Arrays.asList(75D, 120D, 175D);
        assertThat(result, is(expected));
    }

    @Test
    public void whenIndicativeFunctionThenLinearResults() {
        ScopeInside function = new ScopeInside();
        List<Double> result = function.diapason(5, 8, x -> Math.pow(2, x));
        List<Double> expected = Arrays.asList(32D, 64D, 128D);
        assertThat(result, is(expected));
    }
}