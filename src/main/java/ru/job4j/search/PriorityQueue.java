package ru.job4j.search;

import java.util.LinkedList;

/**
 * Класс описывает работу простейшей очереди по приоритету, которая работает
 * по принципу FIFO
 * @author Stas korobeynikov & Vitaly Khagai
 * @version 1.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * метод принимает на вход заявку и добавляет ее в очередь
     * Если встречается 2 задания с одинаковым приоритетом, то в очереди
     * они распределяются по принципу FIFO
     * @param task задача, которая добавляется в очередь
     */
    public void put(Task task) {
        var index = 0;
        for (var element : tasks) {
            if (task.getPriority() < element.getPriority()) {
                break;
            }
            index++;
        }
        this.tasks.add(index, task);
    }

    /**
     * метод позволяет получить первую задачу в очереди
     * @return возвращает задачу из головы очереди или null, если очередь пуста
     */
    public Task take() {
        return tasks.poll();
    }
}