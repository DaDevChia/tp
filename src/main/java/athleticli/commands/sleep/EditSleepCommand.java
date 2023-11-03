package athleticli.commands.sleep;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Executes the edit sleep commands provided by the user.
 */
public class EditSleepCommand extends Command {

    private static final Logger logger = Logger.getLogger(EditSleepCommand.class.getName());
    private final int index;

    /**
     * Constructor for EditSleepCommand.
     * @param index Index of the sleep to be edited.
     * @param from New start time of the sleep.
     * @param to New end time of the sleep.
     */
    public EditSleepCommand(int index, LocalDateTime from, LocalDateTime to) {
        this.index = index;
        logger.fine("Creating EditSleepCommand with index: " + index + " from: " + from + " and to: " + to);
    }
    
    /**
     * Edits the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleeps = data.getSleeps();
        try {
            final Sleep sleep = sleeps.get(index-1);
            sleeps.remove(sleep);
            assert index > 0 : "Index cannot be less than 0";
            assert index <= sleeps.size() : "Index cannot be more than size of sleep list";
            return new String[]{Message.MESSAGE_SLEEP_DELETED, sleep.toString(),
                    String.format(Message.MESSAGE_SLEEP_COUNT, sleeps.size())};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_INDEX_OUT_OF_BOUNDS);
        }
    }
}
