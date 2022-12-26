package foundation;

import java.time.LocalDateTime;

public class MonthlyTask implements  Repeatability {
    @Override
    public LocalDateTime getNextPeriod(LocalDateTime dateTime) {
        return dateTime.plusMonths(1);
    }

    @Override
    public String toString() {
        return "Ежемесячная";
    }
}
