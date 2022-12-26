package exception;

public class TaskIdNotFoundException extends Exception {
    private final int id;

    public TaskIdNotFoundException(int id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return "Задача с id " + id + " не найдена!";
    }
}
