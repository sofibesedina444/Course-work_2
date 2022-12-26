package service;

import exception.TaskIdNotFoundException;
import foundation.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private static final Map<Integer, Task> TASKS = new HashMap<>();

    private TaskService() {
    }

    public static void addTask(Task task) {
        TASKS.putIfAbsent(task.getId(), task);
        System.out.println("Задача c id = " + task.getId() + " " + task.getHeader() + " добавлена в ежедневник!");
    }

    public static void removeTask(int id) throws TaskIdNotFoundException {
        if (TASKS.remove(id) == null) {
            throw new TaskIdNotFoundException(id);
        }
    }

    public static Collection<Task> getTasksForDay(LocalDate day) {
        Collection<Task> tasksForDay = new ArrayList<>();
        Collection<Task> tasks = TASKS.values();
        for (Task task : tasks) {
            LocalDateTime currentDateTime = task.getDateTime();
            if (currentDateTime.toLocalDate().equals(day)) {
                tasksForDay.add(task);
                continue;
            }
            LocalDateTime nextDateTime = currentDateTime;
            do {
                nextDateTime = task.getRepeatability().getNextPeriod(nextDateTime);
                if (nextDateTime == null) {
                    break;
                }
                if (nextDateTime.toLocalDate().equals(day)) {
                    tasksForDay.add(task);
                    break;
                }
            }
            while (nextDateTime.toLocalDate().isBefore(day));
        }
        return tasksForDay;
    }
}
