package athleticli.commands.sleep;

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

    /**
     * Constructor for AddSleepCommand.
     * 
     * @param sleep Sleep to be added.
     */
    public AddSleepCommand(Sleep sleep) {
        this.sleep = sleep;
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
        int size = sleeps.size();
        String countMessage;
        if (size > 1) {
            countMessage = String.format(Message.MESSAGE_SLEEP_COUNT, size);
        } else {
            countMessage = String.format(Message.MESSAGE_SLEEP_FIRST, size);
        }
        return new String[] {Message.MESSAGE_SLEEP_ADDED, this.sleep.toString(), countMessage};
    }
}
