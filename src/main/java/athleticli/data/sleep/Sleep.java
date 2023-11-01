package athleticli.data.sleep;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a sleep record.
 */
public class Sleep {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MMMM d, " +
            "yyyy 'at' h:mm a", Locale.ENGLISH);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(
            "yyyy-MM-dd", Locale.ENGLISH);

    private final LocalDateTime startDateTime;
    private final LocalDateTime toDateTime;

    private LocalTime sleepingDuration;

    private final LocalDate sleepDate;

    /**
     * Generates a new sleep record with some basic stats.
     * 
     * @param startDateTime Start time of the sleep.
     * @param toDateTime    End time of the sleep.
     */
    public Sleep(LocalDateTime startDateTime, LocalDateTime toDateTime) {
        this.startDateTime = startDateTime;
        this.toDateTime = toDateTime;
        this.sleepingDuration = calculateSleepingDuration();
        this.sleepDate = calculateSleepDate();
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public LocalDate getSleepDate() {
        return sleepDate;
    }

    public LocalTime getSleepingTime() {
        return sleepingDuration;
    }

    /**
     * Calculate the sleeping duration based on start and end time.
     * Factor in the possibility of sleeping past midnight.
     *
     * @return sleeping duration.
     */
    private LocalTime calculateSleepingDuration() {
        Duration duration = Duration.between(startDateTime, toDateTime);
        long seconds = duration.getSeconds();
        return LocalTime.ofSecondOfDay(seconds);
    }

    /**
     * Calculate the sleep date based on start time.
     * Factor in the possibility of sleeping past midnight.
     * We are assuming that the user sleeps before 6am even if the user sleeps past midnight.
     * @return sleep date.
     */
    private LocalDate calculateSleepDate() {
        if (startDateTime.getHour() < 6) {
            return startDateTime.toLocalDate().minusDays(1);
        } else {
            return startDateTime.toLocalDate();
        }
    }

    /**
     * Returns a single line summary of the sleep record.
     * @return String representation of the sleep record.
     */
    @Override
    public String toString() {
        String sleepingDurationOutput = generateSleepingDurationStringOutput();
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String toDateTimeOutput = generateToDateTimeStringOutput();
        String sleepDateOutput = generateSleepDateStringOutput();
        return "[Sleep]" + " | " + sleepDateOutput + " | " + startDateTimeOutput + 
            " | " + toDateTimeOutput + " | " + sleepingDurationOutput;
    }

    public String generateSleepingDurationStringOutput() {
        String sleepingDurationOutput = "";
        if (sleepingDuration.getHour() != 0) {
            sleepingDurationOutput += sleepingDuration.getHour() + " Hours ";
        }
        if (sleepingDuration.getMinute() != 0) {
            sleepingDurationOutput += sleepingDuration.getMinute() + " Minutes";
        }
        return "Sleeping Duration: " + sleepingDurationOutput;
    }

    public String generateStartDateTimeStringOutput() {
        return "Start Time: " + startDateTime.format(DATE_TIME_FORMATTER);
    }

    public String generateToDateTimeStringOutput() {
        return "End Time: " + toDateTime.format(DATE_TIME_FORMATTER);
    }

    public String generateSleepDateStringOutput() {
        return "Date: " + sleepDate.format(DATE_FORMATTER);
    }

    /**
     * Provides a detailed string representation of the sleep duration.
     * @return String representation of the sleep entry.
     */
    public String toDetailedString() {
        String format = "| %-10s | %-30s |%n";
        StringBuilder sb = new StringBuilder();
    
        sb.append(String.format(format, "----------", "------------------------------"));
        sb.append(String.format(format, "Date", sleepDate));
        sb.append(String.format(format, "Duration", generateSleepingDurationStringOutput()));
        sb.append(String.format(format, "From", startDateTime.format(DATE_TIME_FORMATTER)));
        sb.append(String.format(format, "To", toDateTime.format(DATE_TIME_FORMATTER)));
    
        return sb.toString();
    }
}
