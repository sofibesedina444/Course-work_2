package foundation;

import java.time.LocalDateTime;

public interface Repeatability {
    LocalDateTime getNextPeriod(LocalDateTime dateTime);
}
