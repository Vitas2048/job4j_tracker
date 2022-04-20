package ru.job4j.search;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    public void put(Task task) {
        int index = 0;
        int minPrior = 10;
        for (Task element : tasks) {
            if (minPrior < element.getPriority()) {
                index++;
            }
            minPrior = element.getPriority();
        }
        this.tasks.add(index, task);
    }

    public Task take() {
        return tasks.poll();
    }
}