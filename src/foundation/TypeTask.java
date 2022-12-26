package foundation;

public enum TypeTask {
    PERSONAL("Личная"),
    WORKING("Рабочая");

    private final String type;

    TypeTask(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Тип задачи: " + type + "\n";
    }
}
