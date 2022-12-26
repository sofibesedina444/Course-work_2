package foundation;

import java.time.LocalDateTime;

public class AnnualTask implements Repeatability {
    @Override
    public LocalDateTime getNextPeriod(LocalDateTime dateTime) {
        return dateTime.plusYears(1);
    }

    @Override
    public String toString() {
        return "Ежегодовая";
    }
}
