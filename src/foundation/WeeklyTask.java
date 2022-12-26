package foundation;

import java.time.LocalDateTime;

public class WeeklyTask implements Repeatability {
    @Override
    public LocalDateTime getNextPeriod(LocalDateTime dateTime) {
        return dateTime.minusWeeks(1);
    }

    @Override
    public String toString() {
        return "Еженедельная";
    }
}
