import exception.TaskIdNotFoundException;
import foundation.*;
import service.TaskService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern DATE_TIME_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4} \\d{2}:\\d{2}");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private static final Pattern DATE_PATTERN = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.useDelimiter("\n");
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            addTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTasksForDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Добавить задачу\n2. Удалить задачу\n3. Получить задачу на указанный день\n0. Выход");
    }

    private static void addTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String header = scanner.next();
        System.out.print("Введите описание задачи: ");
        String description = scanner.next();
        TypeTask typeTask = inputTypeTask(scanner);
        LocalDateTime localDateTime = inputDateTime(scanner);
        Repeatability repeatability = inputRepeatability(scanner);

        Task task = new Task(header, description, typeTask, localDateTime, repeatability);
        TaskService.addTask(task);
    }

    private static TypeTask inputTypeTask(Scanner scanner) {
        TypeTask typeTask;
        do {
            System.out.print("Выберите тип задачи:\n1.Личная\n2.Рабочая\nВведите тип задачи: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number != 1 && number != 2) {
                    System.out.println("Введите цифру 1 или 2");
                    continue;
                }
                if (number == 1) {
                    typeTask = TypeTask.PERSONAL;
                } else {
                    typeTask = TypeTask.WORKING;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return typeTask;
    }

    private static LocalDateTime inputDateTime(Scanner scanner) {
        LocalDateTime localDateTime;
        do {
            System.out.print("Введите дату и время задачи в формате \"00.00.0000 00:00\": ");
            if (scanner.hasNext(DATE_TIME_PATTERN)) {
                localDateTime = parseDateTime(scanner.next(DATE_TIME_PATTERN));
                if (localDateTime == null) {
                    System.out.println("Введен некорректный формат даты и времени!");
                    continue;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return localDateTime;
    }

    private static LocalDateTime parseDateTime(String localDateTime) {
        try {
            return LocalDateTime.parse(localDateTime, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static Repeatability inputRepeatability(Scanner scanner) {
        Repeatability repeatability;
        do {
            System.out.print("Выберите тип повторяемости задачи:\n1.Однократная\n2.Ежедневная\n3.Еженедельная\n" +
                    "4.Ежемесячная\n5.Ежегодовая\nВведите тип повторяемости: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 1 || number > 5) {
                    System.out.println("Введите цифру 1 до 5");
                    continue;
                }
                switch (number) {
                    default:
                    case 1:
                        repeatability = new SingleTask();
                        break;
                    case 2:
                        repeatability = new DailyTask();
                        break;
                    case 3:
                        repeatability = new WeeklyTask();
                        break;
                    case 4:
                        repeatability = new MonthlyTask();
                        break;
                    case 5:
                        repeatability = new AnnualTask();
                        break;
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
        return repeatability;
    }

    private static void removeTask(Scanner scanner) {
        try {
            do {
                System.out.print("введите id задачи: ");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt();
                    TaskService.removeTask(id);
                    System.out.println("Задача с id = " + id + " удалена");
                    break;
                } else {
                    scanner.next();
                }
            }
            while (true);
        } catch (TaskIdNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getTasksForDay(Scanner scanner) {
        do {
            System.out.print("Введите дату в формате \"00.00.0000\": ");
            if(scanner.hasNext(DATE_PATTERN)) {
                LocalDate day = parseDate(scanner.next(DATE_PATTERN));
                if (day == null) {
                    System.out.println("Введите корректную дату");
                    continue;
                }
                Collection<Task> tasksForDay = TaskService.getTasksForDay(day);
                if(tasksForDay.isEmpty()) {
                    System.out.println("Задачи на " + day.format(DATE_FORMATTER) + " не найдены");
                } else {
                    System.out.println("Задачи на " + day.format(DATE_FORMATTER) + ": ");
                    for (Task task : tasksForDay) {
                        System.out.println(task);
                    }
                }
                break;
            } else {
                scanner.next();
            }
        }
        while (true);
    }

    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}