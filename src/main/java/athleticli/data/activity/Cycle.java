package athleticli.data.activity;

import athleticli.parser.Parameter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.time.LocalTime;

/**
 * Represents a cycling activity consisting of relevant evaluation data.
 */
public class Cycle extends Activity {

    private int elevationGain;
    private double averageSpeed;

    /**
     * Generates a new cycling activity with cycling specific stats.
     * By default, calories is 0, i.e., not tracked.
     * averageSpeed is calculated automatically based on the distance and movingTime.
     * @param movingTime duration of the activity in minutes
     * @param distance distance covered in meters
     * @param startDateTime start date and time of the activity
     * @param caption a caption of the activity chosen by the user (e.g., "Morning Run")
     * @param elevationGain elevation gain in meters
     */
    public Cycle(String caption, LocalTime movingTime, int distance, LocalDateTime startDateTime, int elevationGain) {
        super(caption, movingTime, distance, startDateTime);
        this.elevationGain = elevationGain;
        this.averageSpeed = this.calculateAverageSpeed();
    }

    /**
     * Calculates the average speed of the cycle in km/h.
     * @return average speed of the cycle in km/h
     */
    public double calculateAverageSpeed() {
        double dist = (double) this.getDistance();
        double time = (double) this.getMovingTime().toSecondOfDay() / 3600;
        return (dist/1000) / time;
    }

    /**
     * Returns a single line summary of the cycling activity.
     * @return a string representation of the cycle
     */
    @Override
    public String toString() {
        String result = super.toString();
        result = result.replace("[Activity]", "[Cycle]");
        String speedOutput = generateSpeedStringOutput();
        result = result.replace("Time: ", "Speed: " + speedOutput + " | Time: ");
        return result;
    }

    /**
     * Returns a string representation of the average speed of the cycle.
     * @return a string representation of the average speed of the cycle
     */
    public String generateSpeedStringOutput() {
        return String.format(Locale.ENGLISH, "%.2f", this.averageSpeed) + " km/h";
    }

    /**
     * Returns a detailed summary of the cycle.
     * @return a multiline string representation of the cycle
     */
    public String toDetailedString() {
        String startDateTimeOutput = generateStartDateTimeStringOutput();
        String movingTimeOutput = generateMovingTimeStringOutput();
        String distanceOutput = generateDistanceStringOutput();
        String speedOutput = generateSpeedStringOutput();

        int columnWidth = getColumnWidth();
        String header = "[Cycle - " + this.getCaption() + " - " + startDateTimeOutput + "]";
        String firstRow = formatTwoColumns("\t" + distanceOutput, "Elevation Gain: " +
                        elevationGain + " m", columnWidth);
        String secondRow = formatTwoColumns("\t" + movingTimeOutput, "Avg Speed: " +
                        speedOutput, columnWidth);

        return String.join(System.lineSeparator(), header, firstRow, secondRow);
    }

    /**
     * Returns a string representation of the cycle used for storing the data.
     * @return a string representation of the cycle
     */
    @Override
    public String unparse() {
        String commandArgs = super.unparse();
        commandArgs = commandArgs.replace(Parameter.ACTIVITY_STORAGE_INDICATOR, Parameter.CYCLE_STORAGE_INDICATOR);
        commandArgs += " " + Parameter.ELEVATION_SEPARATOR + this.elevationGain;
        return commandArgs;
    }

    public int getElevationGain() {
        return this.elevationGain;
    }

    public void setElevationGain(int elevationGain) {
        this.elevationGain = elevationGain;
    }

    @Override
    public void setDistance(int distance) {
        super.setDistance(distance);
        this.averageSpeed = this.calculateAverageSpeed();
    }

    @Override
    public void setMovingTime(LocalTime movingTime) {
        super.setMovingTime(movingTime);
        this.averageSpeed = this.calculateAverageSpeed();
    }
}
