package foundation;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private static int idCounter = 1;
    private int id;
    private String header;
    private String description;
    private TypeTask typeTask;
    private LocalDateTime dateTime;
    private Repeatability repeatability;

    public Task(String header, String description, TypeTask typeTask, LocalDateTime dateTime, Repeatability repeatability) {
        this.id = idCounter++;
        setHeader(header);
        setDescription(description);
        setTypeTask(typeTask);
        setDateTime(dateTime);
        setRepeatability(repeatability);
    }

    public int getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        if (header != null && !header.isEmpty() && !header.isBlank()) {
            this.header = header;
        } else {
            throw new IllegalArgumentException("Заполните заголовок задачи");
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description != null && !description.isEmpty() && !description.isBlank()) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Заполните описание задачи");
        }
    }

    public TypeTask getTypeTask() {
        return typeTask;
    }

    public void setTypeTask(TypeTask typeTask) {
        if (typeTask == null) {
            typeTask = TypeTask.PERSONAL;
        }
        this.typeTask = typeTask;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            throw new IllegalArgumentException("Задайте корректные дату и время");
        }
        this.dateTime = dateTime;
    }

    public Repeatability getRepeatability() {
        return repeatability;
    }

    public void setRepeatability(Repeatability repeatability) {
        if (repeatability == null) {
            repeatability = new SingleTask();
        }
        this.repeatability = repeatability;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "Задача: " + "\"" + header + "\"" + "\n" +
                "Описание: " + description + "\n" +
                typeTask + "\n" +
                "Дата и время: " + dateTime + "\n" +
                "Повторяемость задачи: " + repeatability + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

