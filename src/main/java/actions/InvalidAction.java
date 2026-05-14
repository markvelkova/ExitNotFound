package actions;

import commands.Command;
import enums.CommandType;
import ui.NarratorsMouth;

import java.util.EnumSet;

/**
 * Action that handles invalid or unrecognized commands from the player.
 *
 * This action is triggered when the player enters a command that is not recognized
 * or is formatted incorrectly. It displays an error message to guide the player.
 */
public class InvalidAction extends Action {

    private Command command;

    /**
     * Constructs an InvalidAction for the given invalid command.
     *
     * @param command the invalid command that triggered this action
     */
    public InvalidAction(Command command) {
        this.supportedCommands =
                EnumSet.of(CommandType.invalid, CommandType.empty);
        this.command = command;
    }

    /**
     * Executes the action by displaying an error message.
     *
     * @return true if the command is invalid and error was displayed, false otherwise
     */
    @Override
    public boolean execute() {
        if (supportedCommands.contains(command.getType())) {
            NarratorsMouth.errorMessage();
            return true;
        }
        return false;
    }
}