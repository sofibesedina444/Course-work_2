package foundation;

import java.time.LocalDateTime;

public class SingleTask implements Repeatability {
    @Override
    public LocalDateTime getNextPeriod(LocalDateTime dateTime) {
        return null;
    }

    @Override
    public String toString() {
        return "Однократная";
    }
}
