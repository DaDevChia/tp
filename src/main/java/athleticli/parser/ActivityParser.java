package athleticli.parser;

import java.math.BigInteger;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import athleticli.data.Goal;
import athleticli.data.activity.Activity;
import athleticli.data.activity.ActivityGoal;
import athleticli.data.activity.Cycle;
import athleticli.data.activity.Run;
import athleticli.data.activity.Swim;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

import static athleticli.parser.Parser.getValueForMarker;

public class ActivityParser {
    //@@author  AlWo223
    /**
     * Parses the index of an activity from a string input.
     *
     * @param commandArgs Raw user input containing the index.
     * @return The parsed Integer index.
     * @throws AthletiException If the input is empty or not a valid integer.
     */
    public static int parseActivityIndex(String commandArgs) throws AthletiException {
        final String commandArgsTrimmed = commandArgs.trim();
<<<<<<< HEAD
        int index;
=======
        if (commandArgsTrimmed.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_EMPTY);
        }

>>>>>>> master
        try {
            return Integer.parseInt(commandArgsTrimmed);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_INDEX_INVALID);
        }
    }

    /**
     * Parses the provided updated activity for the edit command.
     *
<<<<<<< HEAD
     * @param arguments The raw user input containing the updated activity.
     * @return activity             The parsed Activity object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseActivityEdit(String arguments) throws AthletiException {
        try {
            return parseActivity(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
=======
     * @param arguments Raw user input containing the updated activity.
     * @return The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseActivityEdit(String arguments) throws AthletiException {
        String[] parts = arguments.split("(?<=\\d)(?=\\D)", 2);
        if (parts.length < 2) {
>>>>>>> master
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }

        return parseActivityChanges(parts[1]);
    }

    /**
     * Parses the provided updated specific activity for the edit command.
     *
     * @param arguments Raw user input containing the updated activity.
     * @param isRunCycle Whether the activity is a (run or cycle) or not.
     * @return The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    private static ActivityChanges parseActivityTypeEdit(String arguments, boolean isRunCycle) throws AthletiException {
        String[] parts = arguments.split(" ", 2);
        if (parts.length < 2) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }

        if (isRunCycle) {
            return parseRunCycleChanges(parts[1]);
        } else {
            return parseSwimChanges(parts[1]);
        }
    }

    /**
     * Parses the provided updated run or cycle for the edit command
     *
     * @param arguments Raw user input containing the updated run or cycle.
     * @return The parsed ActivityChanges object
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseRunCycleEdit(String arguments) throws AthletiException {
        return parseActivityTypeEdit(arguments, true);
    }

    /**
     * Parses the provided update swim for the edit command
     *
     * @param arguments Raw user input containing the updated swim.
     * @return The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseSwimEdit(String arguments) throws AthletiException {
        return parseActivityTypeEdit(arguments, false);
    }

    /**
<<<<<<< HEAD
     * Parses the provided updated run for the edit command
     *
     * @param arguments The raw user input containing the updated run.
     * @return activity             The parsed run object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseRunEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], true);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the provided updated cycle for the edit command
     *
     * @param arguments The raw user input containing the updated cycle.
     * @return activity             The parsed cycle object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseCycleEdit(String arguments) throws AthletiException {
        try {
            return parseRunCycle(arguments.split(" ", 2)[1], false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
    }

    /**
     * Parses the provided update swim for the edit command
     *
     * @param arguments The raw user input containing the updated swim.
     * @return activity             The parsed swim object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseSwimEdit(String arguments) throws AthletiException {
        try {
            return parseSwim(arguments.split(" ", 2)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
=======
     * Parses the provided swim arguments of the edit command.
     *
     * @param arguments The raw user input containing the updated swim.
     * @return The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseSwimChanges(String arguments) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseChangeArguments(activityChanges, arguments,
                Parameter.CAPTION_SEPARATOR,
                Parameter.DURATION_SEPARATOR,
                Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR,
                Parameter.SWIMMING_STYLE_SEPARATOR);
        return activityChanges;
    }

    /**
     * Parses the provided run or cycle arguments of the edit command.
     *
     * @param arguments The raw user input containing the updated run or cycle.
     * @return The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseRunCycleChanges(String arguments) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseChangeArguments(activityChanges, arguments,
                Parameter.CAPTION_SEPARATOR,
                Parameter.DURATION_SEPARATOR,
                Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR,
                Parameter.ELEVATION_SEPARATOR);
        return activityChanges;
    }

    /**
     * Parses the provided activity arguments of the edit command.
     *
     * @param arguments         The raw user input containing the updated activity.
     * @return                  The parsed ActivityChanges object.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityChanges parseActivityChanges(String arguments) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseChangeArguments(activityChanges, arguments,
                Parameter.CAPTION_SEPARATOR,
                Parameter.DURATION_SEPARATOR,
                Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR);
        return activityChanges;
    }

    /**
     * Parses the provided arguments based on the list of separators and updates the ActivityChanges object.
     *
     * @param activityChanges   ActivityChanges object which contains the updates.
     * @param arguments         Raw user arguments containing the updated parameters.
     * @param separators        List of separators to be used.
     * @throws AthletiException If the input format is invalid.
     */
    private static void parseChangeArguments(ActivityChanges activityChanges, String arguments, String... separators)
            throws AthletiException {
        int numChanges = 0;
        int previousIndex = -1;
        for (int i = 0; i < separators.length; i++) {
            String separator = separators[i];
            int currentSeparatorStartIndex = arguments.indexOf(separator);

            if (currentSeparatorStartIndex != -1) {
                if (previousIndex > currentSeparatorStartIndex) {
                    throw new AthletiException(Message.MESSAGE_ACTIVITY_ORDER_INVALID);
                }

                previousIndex = currentSeparatorStartIndex;
                int currentEndIndex = findNextSeparatorIndex(arguments, currentSeparatorStartIndex, separators, i);

                String segment =
                        arguments.substring(currentSeparatorStartIndex + separator.length(), currentEndIndex).trim();
                parseSegment(activityChanges, segment, separator);
                numChanges++;
            }
        }

        if (numChanges == 0) {
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_EMPTY);
>>>>>>> master
        }
    }

    /**
<<<<<<< HEAD
     * Parses the index of an activity update for the edit command.
=======
     * Finds the index of the next separator in the arguments String
     *
     * @param arguments Raw user input containing the arguments.
     * @param startIndex The String position index to start searching from.
     * @param separators List of separators to search for.
     * @param currentSeparatorIndex Index of the current separator, refers to the list of separators.
     * @return The String position index of the next separator.
     */
    private static int findNextSeparatorIndex(String arguments, int startIndex, String[] separators,
                                              int currentSeparatorIndex) {
        int endIndex = arguments.length();
        for (int j = currentSeparatorIndex + 1; j < separators.length; j++) {
            int nextIndex = arguments.indexOf(separators[j],
                    startIndex + separators[currentSeparatorIndex].length());
            if (nextIndex != -1) {
                endIndex = nextIndex;
                break;
            }
        }
        return endIndex;
    }

    /**
     * General method to parse a segment of the activity changes.
     *
     * @param activityChanges   The ActivityChanges object which keeps track of the updates.
     * @param segment           The segment of the arguments to be parsed.
     * @param separator         The separator used to identify the segment.
     * @throws AthletiException If the input is invalid or empty.
     */
    public static void parseSegment(ActivityChanges activityChanges, String segment, String separator)
            throws AthletiException {
        switch (separator) {
        case Parameter.CAPTION_SEPARATOR:
            checkEmptyCaptionArgument(segment);
            activityChanges.setCaption(segment);
            break;
        case Parameter.DURATION_SEPARATOR:
            checkEmptyDurationArgument(segment);
            activityChanges.setDuration(parseDuration(segment));
            break;
        case Parameter.DISTANCE_SEPARATOR:
            checkEmptyDistanceArgument(segment);
            activityChanges.setDistance(parseDistance(segment));
            break;
        case Parameter.DATETIME_SEPARATOR:
            checkEmptyDateTimeArgument(segment);
            activityChanges.setStartDateTime(Parser.parseDateTime(segment));
            break;
        case Parameter.ELEVATION_SEPARATOR:
            checkEmptyElevationArgument(segment);
            activityChanges.setElevation(parseElevation(segment));
            break;
        case Parameter.SWIMMING_STYLE_SEPARATOR:
            checkEmptySwimmingStyleArgument(segment);
            activityChanges.setSwimmingStyle(parseSwimmingStyle(segment));
            break;
        default:
            assert false: "Invalid separator detected during parsing of activity changes";
        }
    }

    /**
     * Parses the index of an activity update for the edit command from the provided arguments.
>>>>>>> master
     *
     * @param arguments The raw user input containing the index.
     * @return The parsed Integer index.
     * @throws AthletiException If the input format is invalid.
     */
    public static int parseActivityEditIndex(String arguments) throws AthletiException {
<<<<<<< HEAD
        try {
            return parseActivityIndex(arguments.split(" ", 2)[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
=======
        String[] parts = arguments.split("(?<=\\d)(?=\\D)", 2);
        if (parts.length == 0 || parts[0].trim().isEmpty()) {
>>>>>>> master
            throw new AthletiException(Message.MESSAGE_ACTIVITY_EDIT_INVALID);
        }
        return parseActivityIndex(parts[0]);
    }

    /**
     * Parses the raw user input for viewing the activity list and returns whether the user wants the detailed view
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return Whether the user wants the detailed view.
     */
    public static boolean parseActivityListDetail(String commandArgs) {
        return commandArgs.toLowerCase().contains(Parameter.DETAIL_FLAG);
    }

    /**
<<<<<<< HEAD
     * Parses the raw user input for an activity and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);

        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);

        return new Activity(caption, durationParsed, distanceParsed, datetimeParsed);
    }

    /**
=======
>>>>>>> master
     * Parses the raw activity duration input provided by the user.
     *
     * @param duration The raw user input containing the duration.
     * @return The parsed LocalTime duration.
     * @throws AthletiException If the input is not an integer.
     */
    public static LocalTime parseDuration(String duration) throws AthletiException {
        LocalTime durationParsed;
        try {
            durationParsed = LocalTime.parse(duration);
        } catch (DateTimeParseException e) {
            throw new AthletiException(Message.MESSAGE_DURATION_INVALID);
        }
        return durationParsed;
    }

    /**
     * Parses the raw activity distance input provided by the user.
     *
     * @param distance The raw user input containing the distance.
     * @return The parsed Integer distance.
     * @throws AthletiException If the input is not an integer.
     */
    public static int parseDistance(String distance) throws AthletiException {
        BigInteger distanceParsed;
        try {
            distanceParsed = new BigInteger(distance);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_INVALID);
        }
        if (distanceParsed.compareTo(BigInteger.ZERO) < 0) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_NEGATIVE);
        }
        if (distanceParsed.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_TOO_LARGE);
        }
<<<<<<< HEAD
        if (distanceIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_MISSING);
        }
        if (datetimeIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DATETIME_MISSING);
        }
    }

    /**
     * Parses the raw user input for a run or cycle and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseRunCycle(String arguments, boolean isRun) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);
        final int elevationIndex = arguments.indexOf(Parameter.ELEVATION_SEPARATOR);

        checkMissingRunCycleArguments(durationIndex, distanceIndex, datetimeIndex, elevationIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), elevationIndex)
                        .trim();
        final String elevation =
                arguments.substring(elevationIndex + Parameter.ELEVATION_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, elevation);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
        final int elevationParsed = parseElevation(elevation);

        if (isRun) {
            return new Run(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        } else {
            return new Cycle(caption, durationParsed, distanceParsed, datetimeParsed, elevationParsed);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating a run or cycle.
     *
     * @param durationIndex  The position of the duration separator.
     * @param distanceIndex  The position of the distance separator.
     * @param datetimeIndex  The position of the datetime separator.
     * @param elevationIndex The position of the elevation separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingRunCycleArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                                     int elevationIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (elevationIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_MISSING);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating a swim.
     *
     * @param durationIndex      The position of the duration separator.
     * @param distanceIndex      The position of the distance separator.
     * @param datetimeIndex      The position of the datetime separator.
     * @param swimmingStyleIndex The position of the swimming style separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingSwimArguments(int durationIndex, int distanceIndex, int datetimeIndex,
                                                 int swimmingStyleIndex) throws AthletiException {
        checkMissingActivityArguments(durationIndex, distanceIndex, datetimeIndex);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    /**
     * Checks if the raw user input includes any empty arguments for creating an activity.
     *
     * @param caption  The caption of the activity.
     * @param duration The duration of the activity.
     * @param distance The distance of the activity.
     * @param datetime The datetime of the activity.
     * @throws AthletiException If any of the arguments are empty.
     */
    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime) throws AthletiException {
=======
        return distanceParsed.intValue();
    }

    /**
     * Checks if the raw user input includes an empty caption argument.
     *
     * @param caption  The caption of the activity.
     * @throws AthletiException If the argument is empty.
     */
    public static void checkEmptyCaptionArgument(String caption) throws AthletiException {
>>>>>>> master
        if (caption.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_CAPTION_EMPTY);
        }
        if (duration.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DURATION_EMPTY);
        }
        if (distance.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DISTANCE_EMPTY);
        }
        if (datetime.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_DATETIME_EMPTY);
        }
    }

    /**
<<<<<<< HEAD
     * Checks if the raw user input includes any empty arguments for creating a cycle or run.
     *
     * @param caption   The caption of the activity.
     * @param duration  The duration of the activity.
     * @param distance  The distance of the activity.
     * @param datetime  The datetime of the activity.
     * @param elevation The elevation of the activity.
     * @throws AthletiException If any of the arguments are empty.
     */
    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime,
                                                   String elevation) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (elevation.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_EMPTY);
        }
    }

    /**
     * Checks if the raw user input includes any empty arguments for creating a swim.
     *
     * @param caption            The caption of the activity.
     * @param duration           The duration of the activity.
     * @param distance           The distance of the activity.
     * @param datetime           The datetime of the activity.
     * @param swimmingStyleIndex The position of the swimming style separator.
     * @throws AthletiException If any of the arguments are empty.
     */
    public static void checkEmptyActivityArguments(String caption, String duration, String distance,
                                                   String datetime,
                                                   int swimmingStyleIndex) throws AthletiException {
        checkEmptyActivityArguments(caption, duration, distance, datetime);
        if (swimmingStyleIndex == -1) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
        }
    }

    /**
     * Parses the raw user input for a swim and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return activity         An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseSwim(String arguments) throws AthletiException {
        final int durationIndex = arguments.indexOf(Parameter.DURATION_SEPARATOR);
        final int distanceIndex = arguments.indexOf(Parameter.DISTANCE_SEPARATOR);
        final int datetimeIndex = arguments.indexOf(Parameter.DATETIME_SEPARATOR);
        final int swimmingStyleIndex = arguments.indexOf(Parameter.SWIMMING_STYLE_SEPARATOR);

        checkMissingSwimArguments(durationIndex, distanceIndex, datetimeIndex, swimmingStyleIndex);

        final String caption = arguments.substring(0, durationIndex).trim();
        final String duration =
                arguments.substring(durationIndex + Parameter.DURATION_SEPARATOR.length(), distanceIndex)
                        .trim();
        final String distance =
                arguments.substring(distanceIndex + Parameter.DISTANCE_SEPARATOR.length(), datetimeIndex)
                        .trim();
        final String datetime =
                arguments.substring(datetimeIndex + Parameter.DATETIME_SEPARATOR.length(), swimmingStyleIndex)
                        .trim();
        final String swimmingStyle =
                arguments.substring(swimmingStyleIndex + Parameter.SWIMMING_STYLE_SEPARATOR.length()).trim();

        checkEmptyActivityArguments(caption, duration, distance, datetime, swimmingStyleIndex);

        final LocalTime durationParsed = parseDuration(duration);
        final int distanceParsed = parseDistance(distance);
        final LocalDateTime datetimeParsed = Parser.parseDateTime(datetime);
        final Swim.SwimmingStyle swimmingStyleParsed = parseSwimmingStyle(swimmingStyle);

        return new Swim(caption, durationParsed, distanceParsed, datetimeParsed, swimmingStyleParsed);
    }

    /**
=======
>>>>>>> master
     * Parses the raw user input for a swimming style and returns the corresponding swimming style object.
     *
     * @param swimmingStyle The raw user input containing the swimming style.
     * @return An object representing the swimming style.
     * @throws AthletiException If the input format is invalid.
     */
    public static Swim.SwimmingStyle parseSwimmingStyle(String swimmingStyle) throws AthletiException {
        try {
            return Swim.SwimmingStyle.valueOf(swimmingStyle.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_INVALID);
        }
    }

    /**
     * Parses the raw user input for adding an activity goal and returns the corresponding activity goal object.
     * @param commandArgs       The raw user input containing the arguments.
     * @return An object representing the activity goal.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityGoal parseActivityGoal(String commandArgs) throws AthletiException {
        final int sportIndex = commandArgs.indexOf(Parameter.SPORT_SEPARATOR);
        final int typeIndex = commandArgs.indexOf(Parameter.TYPE_SEPARATOR);
        final int periodIndex = commandArgs.indexOf(Parameter.PERIOD_SEPARATOR);
        final int targetIndex = commandArgs.indexOf(Parameter.TARGET_SEPARATOR);

        checkMissingActivityGoalArguments(sportIndex, typeIndex, periodIndex, targetIndex);

        final String sport = commandArgs.substring(sportIndex + Parameter.SPORT_SEPARATOR.length(), typeIndex).trim();
        final String type =
                commandArgs.substring(typeIndex + Parameter.TYPE_SEPARATOR.length(), periodIndex).trim();
        final String period =
                commandArgs.substring(periodIndex + Parameter.PERIOD_SEPARATOR.length(), targetIndex).trim();
        final String target = commandArgs.substring(targetIndex + Parameter.TARGET_SEPARATOR.length()).trim();

        final ActivityGoal.Sport sportParsed = parseSport(sport);
        final ActivityGoal.GoalType typeParsed = parseGoalType(type);
        final Goal.TimeSpan periodParsed = parsePeriod(period);
        final int targetParsed = parseTarget(target);

        return new ActivityGoal(periodParsed, typeParsed, sportParsed, targetParsed);
    }

    //@@author  nihalzp
    /**
     * Parses the raw user input for deleting an activity goal and returns the corresponding activity goal
     * object.
     *
     * @param commandArgs The raw user input containing the arguments.
     * @return An object representing the activity goal.
     * @throws AthletiException If the input format is invalid.
     */
    public static ActivityGoal parseDeleteActivityGoal(String commandArgs) throws AthletiException {
        final String sport = getValueForMarker(commandArgs, Parameter.SPORT_SEPARATOR);
        if (sport.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_SPORT_MISSING);
        }
        final String type = getValueForMarker(commandArgs, Parameter.TYPE_SEPARATOR);
        if (type.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_TYPE_MISSING);
        }
        final String period = getValueForMarker(commandArgs, Parameter.PERIOD_SEPARATOR);
        if (period.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_PERIOD_MISSING);
        }
        final ActivityGoal.Sport sportParsed = parseSport(sport);
        final ActivityGoal.GoalType typeParsed = parseGoalType(type);
        final Goal.TimeSpan periodParsed = parsePeriod(period);
        return new ActivityGoal(periodParsed, typeParsed, sportParsed, 0);
    }
    //@@author  AlWo223

    /**
     * Parses the sport input provided by the user.
     * @param sport                 The raw user input containing the sport.
     * @return                      The parsed Sport object.
     * @throws AthletiException     If the input format is invalid.
     */
    public static ActivityGoal.Sport parseSport(String sport) throws AthletiException {
        try {
            return ActivityGoal.Sport.valueOf(sport.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_SPORT_INVALID);
        }
    }

    /**
     * Checks if the raw user input is missing any arguments for creating an activity goal.
     * @param sportIndex        The position of the sport separator.
     * @param targetIndex       The position of the target separator.
     * @param periodIndex       The position of the period separator.
     * @param valueIndex        The position of the value separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingActivityGoalArguments(int sportIndex, int targetIndex, int periodIndex,
            int valueIndex) throws AthletiException {
        if (sportIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_SPORT_MISSING);
        }
        if (targetIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_TARGET_MISSING);
        }
        if (periodIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_PERIOD_MISSING);
        }
        if (valueIndex == -1) {
            throw new AthletiException(Message.MESSAGE_ACTIVITYGOAL_TARGET_MISSING);
        }
    }

    /**
     * Parses the raw elevation input provided by the user.
     *
     * @param elevation The raw user input containing the elevation.
     * @return The parsed Integer elevation.
     * @throws AthletiException If the input is not an integer.
     */
    public static int parseElevation(String elevation) throws AthletiException {
        final int ELEVATION_UPPER_BOUNDARY = 10000;
        BigInteger elevationParsed;
        try {
            elevationParsed = new BigInteger(elevation);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_INVALID);
        }
        if (elevationParsed.abs().compareTo(BigInteger.valueOf(ELEVATION_UPPER_BOUNDARY)) > 0) {
            throw new AthletiException(Message.MESSAGE_ELEVATION_TOO_LARGE);
        }
        return elevationParsed.intValue();
    }

    /**
     * Parses the goal type input provided by the user.
     * @param type                The raw user input containing the goal type.
     * @return                    The parsed GoalType object.
     * @throws AthletiException   If the input format is invalid.
     */
    public static ActivityGoal.GoalType parseGoalType(String type) throws AthletiException {
        try {
            return ActivityGoal.GoalType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_TYPE_INVALID);
        }
    }

    /**
     * Parses the period input provided by the user
     * @param period            The raw user input containing the period.
     * @return                  The parsed Period object.
     * @throws AthletiException If the input format is invalid.
     */
    public static Goal.TimeSpan parsePeriod(String period) throws AthletiException {
        try {
            return Goal.TimeSpan.valueOf(period.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AthletiException(Message.MESSAGE_PERIOD_INVALID);
        }
    }

    /**
     * Parses the target input provided by the user.
     * @param target            The raw user input containing the target value.
     * @return                  The parsed Integer target value.
     * @throws AthletiException If the input is not a positive number.
     */
    public static int parseTarget(String target) throws AthletiException {
        BigInteger targetParsed;
        try {
            targetParsed = new BigInteger(target);
        } catch (NumberFormatException e) {
            throw new AthletiException(Message.MESSAGE_TARGET_INVALID);
        }
        if (targetParsed.compareTo(BigInteger.ZERO) < 0) {
            throw new AthletiException(Message.MESSAGE_TARGET_NEGATIVE);
        }
        if (targetParsed.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new AthletiException(Message.MESSAGE_TARGET_TOO_LARGE);
        }
        return targetParsed.intValue();
    }

    /**
     * Parses the raw user input for an activity and returns the corresponding ActivityChanges object containing the
     * data entries for the activity.
     *
     * @param arguments The raw user input containing the arguments.
     * @throws AthletiException If the input format is invalid.
     */
    public static void parseActivityArguments(ActivityChanges activityChanges, String arguments,
                                                         String... separators) throws AthletiException {
        int firstSeparatorIndex = arguments.indexOf(separators[0]);
        if (firstSeparatorIndex == -1) {
            throw new AthletiException(Message.MESSAGE_DURATION_MISSING);
        }
        final String caption = arguments.substring(0, firstSeparatorIndex).trim();
        if (caption.isEmpty()) {
            throw new AthletiException(Message.MESSAGE_CAPTION_EMPTY);
        }
        activityChanges.setCaption(caption);

        int previousIndex = -1;
        for (int i = 0; i < separators.length; i++) {
            String separator = separators[i];
            int currentSeparatorStartIndex = arguments.indexOf(separator);
            checkMissingActivityArgument(currentSeparatorStartIndex, separator);

            if (previousIndex > currentSeparatorStartIndex) {
                throw new AthletiException(Message.MESSAGE_ACTIVITY_ORDER_INVALID);
            }
            previousIndex = currentSeparatorStartIndex;

            int currentEndIndex = findNextSeparatorIndex(arguments, currentSeparatorStartIndex, separators, i);

            String segment =
                    arguments.substring(currentSeparatorStartIndex + separator.length(), currentEndIndex).trim();
            parseSegment(activityChanges, segment, separator);
        }

    }

    /**
     * Checks if argument related to the separator is missing and throws parameter specific exception.
     * @param separatorIndex The position of the separator, refers to the list of separators.
     * @param separator The separator.
     * @throws AthletiException If any of the arguments are missing.
     */
    public static void checkMissingActivityArgument(int separatorIndex, String separator) throws AthletiException {
        if (separatorIndex == -1) {
            switch (separator) {
            case Parameter.DURATION_SEPARATOR:
                throw new AthletiException(Message.MESSAGE_DURATION_MISSING);
            case Parameter.DISTANCE_SEPARATOR:
                throw new AthletiException(Message.MESSAGE_DISTANCE_MISSING);
            case Parameter.DATETIME_SEPARATOR:
                throw new AthletiException(Message.MESSAGE_DATETIME_MISSING);
            case Parameter.ELEVATION_SEPARATOR:
                throw new AthletiException(Message.MESSAGE_ELEVATION_MISSING);
            case Parameter.SWIMMING_STYLE_SEPARATOR:
                throw new AthletiException(Message.MESSAGE_SWIMMINGSTYLE_MISSING);
            default:
                assert false: "Invalid separator detected during parsing of activity";
            }
        }
    }

    /**
     * Parses the raw user input for a run or cycle and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseRunCycle(String arguments, boolean isRun) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseActivityArguments(activityChanges, arguments,
                Parameter.DURATION_SEPARATOR, Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR, Parameter.ELEVATION_SEPARATOR);

        if (isRun) {
            return new Run(activityChanges.getCaption(), activityChanges.getDuration(),
                    activityChanges.getDistance(), activityChanges.getStartDateTime(),
                    activityChanges.getElevation());
        } else {
            return new Cycle(activityChanges.getCaption(), activityChanges.getDuration(),
                    activityChanges.getDistance(), activityChanges.getStartDateTime(),
                    activityChanges.getElevation());
        }
    }

    /**
     * Parses the raw user input for an activity and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseActivity(String arguments) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseActivityArguments(activityChanges, arguments,
                Parameter.DURATION_SEPARATOR, Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR);
        return new Activity(activityChanges.getCaption(), activityChanges.getDuration(),
                activityChanges.getDistance(), activityChanges.getStartDateTime());
    }

    /**
     * Parses the raw user input for a swim and returns the corresponding activity object.
     *
     * @param arguments The raw user input containing the arguments.
     * @return activity         An object representing the activity.
     * @throws AthletiException If the input format is invalid.
     */
    public static Activity parseSwim(String arguments) throws AthletiException {
        ActivityChanges activityChanges = new ActivityChanges();
        parseActivityArguments(activityChanges, arguments,
                Parameter.DURATION_SEPARATOR, Parameter.DISTANCE_SEPARATOR,
                Parameter.DATETIME_SEPARATOR, Parameter.SWIMMING_STYLE_SEPARATOR);
        return new Swim(activityChanges.getCaption(), activityChanges.getDuration(),
                activityChanges.getDistance(), activityChanges.getStartDateTime(),
                activityChanges.getSwimmingStyle());
    }
}
