package ru.job4j.collection;

import org.junit.Test;
import java.util.Comparator;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

public class JobTest {
    @Test
    public void whenCompatorByNameAndPrority() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByAscName() {
        Comparator<Job> cmpName = new JobAscByName();
        int rsl = cmpName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompatorByAscPriority() {
        Comparator<Job> cmpPrior = new JobAscByPriority();
        int rsl = cmpPrior.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByDscNameAscPriority() {
        Comparator<Job> cmpName = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpName.compare(
                new Job("B", 0),
                new Job("B", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenCompatorByDscNameDscPriority() {
        Comparator<Job> cmpName = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpName.compare(
                new Job("B", 1),
                new Job("B", 15)
        );
        assertThat(rsl, greaterThan(0));
    }
}