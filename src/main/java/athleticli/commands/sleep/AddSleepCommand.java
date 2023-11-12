package athleticli.commands.sleep;

<<<<<<< HEAD
=======
import java.util.logging.Logger;
>>>>>>> master
import athleticli.commands.Command;
import athleticli.data.Data;
import athleticli.data.sleep.Sleep;
import athleticli.data.sleep.SleepList;
import athleticli.ui.Message;

/**
 * Executes the add sleep commands provided by the user.
 */
public class AddSleepCommand extends Command {
    private final Sleep sleep;
<<<<<<< HEAD
=======
    private final Logger logger = Logger.getLogger(AddSleepCommand.class.getName());
>>>>>>> master

    /**
     * Constructor for AddSleepCommand.
     * 
     * @param sleep Sleep to be added.
     */
    public AddSleepCommand(Sleep sleep) {
        this.sleep = sleep;
<<<<<<< HEAD
=======
        logger.fine("Creating AddSleepCommand with sleep: " + sleep.toString());
        assert sleep.getStartDateTime() != null : "Start time cannot be null";
        assert sleep.getEndDateTime() != null : "End time cannot be null";
        assert sleep.getStartDateTime().isBefore(sleep.getEndDateTime()) : "Start time must be before end time";
>>>>>>> master
    }

    /**
     * Adds the sleep record to the sleep list.
     * 
     * @param data The current data containing the sleep list.
     * @return The message which will be shown to the user.
     */
    @Override
    public String[] execute(Data data) {
        SleepList sleeps = data.getSleeps();
        sleeps.add(this.sleep);
        sleeps.sort();
        int size = sleeps.size();
<<<<<<< HEAD
=======
        logger.info("Added sleep: " + this.sleep.toString());
        logger.info("Sleep count: " + sleeps.size());
        logger.info("Sleep list: " + sleeps.toString());
>>>>>>> master
        String countMessage;
        if (size > 1) {
            countMessage = String.format(Message.MESSAGE_SLEEP_COUNT, size);
        } else {
            countMessage = String.format(Message.MESSAGE_SLEEP_FIRST, size);
        }
        return new String[] {Message.MESSAGE_SLEEP_ADDED, this.sleep.toString(), countMessage};
    }
}
