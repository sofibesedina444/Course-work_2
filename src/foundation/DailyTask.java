package foundation;

import java.time.LocalDateTime;

public class DailyTask implements Repeatability {
    @Override
    public LocalDateTime getNextPeriod(LocalDateTime dateTime) {
        return dateTime.plusDays(1);
    }

    @Override
    public String toString() {
        return "Ежедневная";
    }
}
