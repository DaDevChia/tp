package athleticli.commands.sleep;

import java.util.logging.Logger;

import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.exceptions.AthletiException;
import athleticli.ui.Message;

/**
 * Executes the edit sleep command provided by the user.
 */
public class EditSleepCommand extends Command {
    private static final Logger logger = Logger.getLogger(EditSleepCommand.class.getName());
    private final int index;
<<<<<<< HEAD
=======
    private final Sleep newSleep;
>>>>>>> master

    /**
     * Constructor for EditSleepCommand.
     * @param index Index of the sleep to be edited.
     * @param from New start time of the sleep.
     * @param to New end time of the sleep.
     */
    public EditSleepCommand(int index, Sleep newSleep) {
        this.index = index;
<<<<<<< HEAD
        logger.fine("Creating EditSleepCommand with index: " + index + " from: " + from + " and to: " + to);
=======
        this.newSleep = newSleep;
        logger.fine("Creating EditSleepCommand with index: " + index);
>>>>>>> master
    }
    
    /**
     * Edits the sleep record at the specified index.
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    public String[] execute(Data data) throws AthletiException {
        SleepList sleeps = data.getSleeps();
        try {
<<<<<<< HEAD
            final Sleep sleep = sleeps.get(index-1);
            sleeps.remove(sleep);
            assert index > 0 : "Index cannot be less than 0";
            assert index <= sleeps.size() : "Index cannot be more than size of sleep list";
            return new String[]{Message.MESSAGE_SLEEP_DELETED, sleep.toString(),
                    String.format(Message.MESSAGE_SLEEP_COUNT, sleeps.size())};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_INDEX_OUT_OF_BOUNDS);
=======
            final Sleep oldSleep = sleeps.get(index-1);
            sleeps.set(index-1, newSleep);
            logger.info("Activity at index " + index + " successfully edited");
            return new String[]{Message.MESSAGE_SLEEP_EDITED, 
                "original: " + oldSleep,
                "new: " + newSleep};
        } catch (IndexOutOfBoundsException e) {
            throw new AthletiException(Message.ERRORMESSAGE_SLEEP_EDIT_INDEX_OOBE);
>>>>>>> master
        }
    }
}
